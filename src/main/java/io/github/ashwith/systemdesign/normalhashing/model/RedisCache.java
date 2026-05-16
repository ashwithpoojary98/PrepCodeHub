package io.github.ashwith.systemdesign.normalhashing.model;

public class RedisCache<T> {
    private final String key;
    private final T value;
    private final long expireTime;


    public RedisCache(String key, T value, long expireTime) {
        this.key = key;
        this.value = value;
        this.expireTime = expireTime;
    }

    public String getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public boolean isExpired() {
        return System.currentTimeMillis()-expireTime > 0;
    }

}
