package io.github.ashwith.systemdesign.normalhashing.model;

import io.github.ashwith.systemdesign.normalhashing.exceptions.RedisConfigurationException;

public class RedisConfiguration {

    private final int nodeCount;
    private final int defaultExpireTime;
    private final double loadFactor;
    private final int cacheSize;

    public RedisConfiguration(RedisConfigurationBuilder redisConfiguration) {
        this.nodeCount = redisConfiguration.nodeCount;
        this.defaultExpireTime = redisConfiguration.defaultExpireTime;
        this.loadFactor = redisConfiguration.loadFactor;
        this.cacheSize = redisConfiguration.cacheSize;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public int getDefaultExpireTime() {
        return defaultExpireTime;
    }

    public double getLoadFactor() {
        return loadFactor;
    }

    public int getCacheSize() {
        return cacheSize;
    }

    @Override
    public String toString() {
        return String.format(
                "RedisConfiguration[nodeCount=%d, defaultExpireTime=%dms, loadFactor=%.2f, cacheSize=%d]",
                nodeCount, defaultExpireTime, loadFactor, cacheSize
        );
    }


    public static class RedisConfigurationBuilder {

        private int nodeCount = 3;
        private int defaultExpireTime = 30;
        private double loadFactor = 0.75;
        private int cacheSize = 5;

        public RedisConfigurationBuilder nodeCount(int nodeCount) {
            this.nodeCount = nodeCount;
            return this;
        }

        public RedisConfigurationBuilder defaultExpireTime(int defaultExpireTime) {
            this.defaultExpireTime = defaultExpireTime;
            return this;
        }

        public RedisConfigurationBuilder loadFactor(double loadFactor) {
            this.loadFactor = loadFactor;
            return this;
        }

        public RedisConfigurationBuilder cacheSize(int cacheSize) {
            this.cacheSize = cacheSize;
            return this;
        }

        public RedisConfiguration build() {
            if (this.nodeCount <= 0 || this.defaultExpireTime <= 0) {
                throw new RedisConfigurationException(String.format("Node count %s and default expire time %s is invalid.Both should be greater than 1", nodeCount, defaultExpireTime));
            }
            if (loadFactor <= 0 || loadFactor > 1) {
                throw new RedisConfigurationException(String.format("Load factor %s is invalid.", loadFactor));
            }
            if (cacheSize <= 0) {
                throw new RedisConfigurationException(String.format("Cache size %s is invalid.", cacheSize));
            }
            return new RedisConfiguration(this);
        }
    }
}
