package io.github.ashwith.systemdesign.snakeladder.model;

import io.github.ashwith.systemdesign.snakeladder.exceptions.BoardCreationException;

public class Snake {

    private final int head;
    private final int tail;

    public Snake(int head, int tail) {
        if (head < 0 || tail < 0 || head > tail) {
            throw new BoardCreationException("head or tail out of bounds");
        }
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}
