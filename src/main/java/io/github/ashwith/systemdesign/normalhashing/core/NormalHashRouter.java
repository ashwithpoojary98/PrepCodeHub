package io.github.ashwith.systemdesign.normalhashing.core;

import io.github.ashwith.systemdesign.normalhashing.model.RedisServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NormalHashRouter<T> implements HashRouter<T> {

    private final List<RedisServer<T>> allServers;

    public NormalHashRouter(List<RedisServer<T>> allServers) {
        this.allServers = new ArrayList<>(allServers);
    }

    @Override
    public synchronized RedisServer<T> getServer(String key) {
        List<RedisServer<T>> healthyServers = allServers.stream()
                .filter(RedisServer::isHealthy)
                .toList();

        if (healthyServers.isEmpty()) {
            throw new IllegalStateException("No healthy servers available to route key: " + key);
        }

        int index = Math.abs(key.hashCode()) % healthyServers.size();
        return healthyServers.get(index);
    }

    @Override
    public synchronized void addServer(RedisServer<T> server) {
        allServers.add(server);
    }

    @Override
    public synchronized void removeServer(RedisServer<T> server) {
        allServers.remove(server);
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

    public synchronized List<RedisServer<T>> getAllServers() {
        return new ArrayList<>(allServers);
    }

    public synchronized ReshufflingReport simulateAddServerImpact(List<String> keys, RedisServer<T> newServer) {
        Map<String, String> before = captureMapping(keys);
        allServers.add(newServer);
        Map<String, String> after = captureMapping(keys);
        allServers.remove(newServer);
        return buildReport(keys, before, after);
    }

    public synchronized ReshufflingReport simulateRemoveServerImpact(List<String> keys, RedisServer<T> serverToRemove) {
        Map<String, String> before = captureMapping(keys);
        allServers.remove(serverToRemove);
        Map<String, String> after = captureMapping(keys);
        allServers.add(serverToRemove);
        return buildReport(keys, before, after);
    }

    private Map<String, String> captureMapping(List<String> keys) {
        Map<String, String> mapping = new HashMap<>();
        for (String key : keys) {
            try {
                RedisServer<T> server = getServer(key);
                mapping.put(key, server.getServerIP() + ":" + server.getServerPort());
            } catch (IllegalStateException e) {
                mapping.put(key, "UNAVAILABLE");
            }
        }
        return mapping;
    }

    private ReshufflingReport buildReport(List<String> keys,
                                          Map<String, String> before,
                                          Map<String, String> after) {
        int remapped = 0;
        for (String key : keys) {
            if (!before.get(key).equals(after.get(key))) {
                remapped++;
            }
        }
        return new ReshufflingReport(keys.size(), remapped);
    }

    public static class ReshufflingReport {
        private final int totalKeys;
        private final int remappedKeys;

        public ReshufflingReport(int totalKeys, int remappedKeys) {
            this.totalKeys = totalKeys;
            this.remappedKeys = remappedKeys;
        }

        public int getTotalKeys() {
            return totalKeys;
        }

        public int getRemappedKeys() {
            return remappedKeys;
        }

        public double getRemappedPercent() {
            return totalKeys == 0 ? 0 : (remappedKeys * 100.0) / totalKeys;
        }

        @Override
        public String toString() {
            return String.format("ReshufflingReport[total=%d, remapped=%d, remappedPercent=%.1f%%]",
                    totalKeys, remappedKeys, getRemappedPercent());
        }
    }
}
