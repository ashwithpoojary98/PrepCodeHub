package io.github.ashwith.systemdesign.normalhashing.core;

import io.github.ashwith.systemdesign.normalhashing.model.RedisServer;

public interface HashRouter<T> {
    RedisServer<T> getServer(String key);

    void addServer(RedisServer<T> server);

    void removeServer(RedisServer<T> server);
}
