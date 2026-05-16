package io.github.ashwith.systemdesign.normalhashing.exceptions;

public class RedisConfigurationException extends RuntimeException {

    public RedisConfigurationException(String message) {
        super(message);
    }

    public RedisConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
