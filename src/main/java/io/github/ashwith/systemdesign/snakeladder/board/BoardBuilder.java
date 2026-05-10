package io.github.ashwith.systemdesign.snakeladder.board;

import io.github.ashwith.systemdesign.snakeladder.model.Ladder;
import io.github.ashwith.systemdesign.snakeladder.model.Snake;

import java.util.ArrayList;
import java.util.List;

public class BoardBuilder {

    private final List<Snake> snakes = new ArrayList<>();
    private final List<Ladder> ladders = new ArrayList<>();
    private int boardSize;

    public BoardBuilder addSnake(int head, int tail) {
        snakes.add(new Snake(head, tail));
        return this;
    }

    public BoardBuilder boardSize(int boardSize) {
        this.boardSize = boardSize;
        return this;
    }

    public BoardBuilder addLadder(int lowerBound, int upperBound) {
        ladders.add(new Ladder(lowerBound, upperBound));
        return this;
    }

    public IBoard build() {
        Board board = new Board(boardSize);
        for (Snake snake : snakes) {
            board.addSnake(snake);
        }
        for (Ladder ladder : ladders) {
            board.addLadder(ladder);
        }
        return board;

    }
}
