package io.github.ashwith.systemdesign.snakeladder.exceptions;

public class SLGameException extends RuntimeException {
    public SLGameException(String message) {
        super(message);
    }

    public SLGameException(String message, Throwable cause) {
        super(message, cause);
    }
}
