package io.github.ashwith.systemdesign.normalhashing.core;

import io.github.ashwith.systemdesign.normalhashing.model.RedisServer;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ConsistentHashRouter<T> implements HashRouter<T> {

    private final TreeMap<Integer, RedisServer<T>> ring = new TreeMap<>();
    private final int virtualNodes;

    public ConsistentHashRouter(List<RedisServer<T>> servers, int virtualNodes) {
        this.virtualNodes = virtualNodes;
        servers.forEach(this::addServer);
    }

    @Override
    public synchronized void addServer(RedisServer<T> server) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = hash(server.getServerIP() + ":" + server.getServerPort() + "#" + i);
            ring.put(hash, server);
        }
    }

    @Override
    public synchronized void removeServer(RedisServer<T> server) {
        for (int i = 0; i < virtualNodes; i++) {
            int hash = hash(server.getServerIP() + ":" + server.getServerPort() + "#" + i);
            ring.remove(hash);
        }
    }

    @Override
    public synchronized RedisServer<T> getServer(String key) {
        if (ring.isEmpty()) throw new IllegalStateException("No servers on ring");

        int hash = hash(key);

        Map.Entry<Integer, RedisServer<T>> entry = ring.ceilingEntry(hash);
        if (entry == null) entry = ring.firstEntry();

        Set<Integer> visited = new HashSet<>();
        while (!entry.getValue().isHealthy()) {
            visited.add(entry.getKey());
            entry = ring.higherEntry(entry.getKey());
            if (entry == null) entry = ring.firstEntry();
            if (visited.contains(entry.getKey())) {
                throw new IllegalStateException("No healthy servers available");
            }
        }

        return entry.getValue();
    }

    public T get(String key) {
        return getServer(key).getValue(key);
    }

    public void set(String key, T value) {
        getServer(key).setValue(key, value);
    }

    public void delete(String key) {
        getServer(key).deleteValue(key);
    }

    public synchronized int getRingSize() {
        return ring.size();
    }

    private int hash(String key) {
        return Math.abs(key.hashCode());
    }
}
