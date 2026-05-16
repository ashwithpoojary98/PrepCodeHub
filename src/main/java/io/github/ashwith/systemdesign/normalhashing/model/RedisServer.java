package io.github.ashwith.systemdesign.normalhashing.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class RedisServer<T> {

    private final String serverIP;
    private final int serverPort;
    private final RedisConfiguration redisConfiguration;
    private final Map<String, RedisCache<T>> cacheMap;
    private boolean isHealthy;

    public RedisServer(String serverIP, int serverPort, RedisConfiguration redisConfiguration) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.redisConfiguration = redisConfiguration;
        this.isHealthy = true;

        int maxSize = redisConfiguration.getCacheSize();
        this.cacheMap = new LinkedHashMap<>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, RedisCache<T>> eldest) {
                return size() > maxSize;
            }
        };
    }

    public String getServerIP() {
        return serverIP;
    }

    public int getServerPort() {
        return serverPort;
    }

    public RedisConfiguration getRedisConfiguration() {
        return redisConfiguration;
    }

    public synchronized T getValue(String key) {
        RedisCache<T> cache = cacheMap.get(key);
        if (cache == null) {
            return null;
        }
        if (cache.isExpired()) {
            cacheMap.remove(key);
            return null;
        }
        return cache.getValue();
    }

    public synchronized void setValue(String key, T value) {
        long expireAt = System.currentTimeMillis() + redisConfiguration.getDefaultExpireTime();
        cacheMap.put(key, new RedisCache<>(key, value, expireAt));
    }

    public synchronized float checkCurrentLoadFactor() {
        return (float) cacheMap.size() / redisConfiguration.getCacheSize();
    }

    public synchronized boolean isLoadFactorFull() {
        return checkCurrentLoadFactor() >= redisConfiguration.getLoadFactor();
    }

    public synchronized void deleteValue(String key) {
        cacheMap.remove(key);
    }

    public synchronized int clearExpiredKeys() {
        int before = cacheMap.size();
        cacheMap.entrySet().removeIf(entry -> entry.getValue().isExpired());
        return before - cacheMap.size();
    }

    public synchronized int getCurrentCacheSize() {
        return cacheMap.size();
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void setHealthy(boolean healthy) {
        this.isHealthy = healthy;
    }

    @Override
    public String toString() {
        return String.format("RedisServer[ip=%s, port=%d, healthy=%b, cacheSize=%d/%d]",
                serverIP, serverPort, isHealthy, cacheMap.size(), redisConfiguration.getCacheSize());
    }
}
