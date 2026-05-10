package io.github.ashwith.systemdesign.snakeladder.exceptions;

import java.io.Serializable;

public class BoardCreationException extends RuntimeException implements Serializable {

    public BoardCreationException() {
    }

    public BoardCreationException(String message) {
        super(message);
    }

    public BoardCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
