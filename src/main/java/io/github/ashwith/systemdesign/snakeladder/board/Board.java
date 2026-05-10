package io.github.ashwith.systemdesign.snakeladder.board;

import io.github.ashwith.systemdesign.snakeladder.exceptions.BoardCreationException;
import io.github.ashwith.systemdesign.snakeladder.model.Ladder;
import io.github.ashwith.systemdesign.snakeladder.model.Snake;

import java.util.HashMap;
import java.util.Map;

public class Board implements IBoard {

    private final int boardSize;
    private final Map<Integer, Integer> snakes = new HashMap<>();
    private final Map<Integer, Integer> ladders = new HashMap<>();

    public Board(int boardSize) {
        if (boardSize <= 0) throw new BoardCreationException("Board size must be positive");
        this.boardSize = boardSize;
    }

    @Override
    public int getBoardSize() {
        return boardSize;
    }

    public void addSnake(Snake snake) {
        if (snakes.containsKey(snake.getHead())) {
            throw new BoardCreationException("snake already exists");
        } else {
            snakes.put(snake.getHead(), snake.getTail());
        }
    }

    public void addLadder(Ladder ladder) {
        if (ladders.containsKey(ladder.getLowerEnd())) {
            throw new BoardCreationException("ladder already exists");
        } else {
            ladders.put(ladder.getLowerEnd(), ladder.getUpperEnd());
        }
    }


    @Override
    public int getCurrentPosition(int currentPosition, int diceTurnedValue) {
        int finalPosition = currentPosition + diceTurnedValue;
        if (finalPosition > boardSize || finalPosition < 0) {
            System.out.println("Wait till again your turn!!");
            return currentPosition;
        }
        if (snakes.containsKey(finalPosition)) {

            int snakePosition = snakes.get(finalPosition);
            System.out.println("Enter snake position: " + snakePosition);
            return snakePosition;
        }
        if (ladders.containsKey(finalPosition)) {
            int ladderPosition = ladders.get(finalPosition);
            System.out.println("Enter ladder position: " + ladderPosition);
            return ladderPosition;
        }
        return finalPosition;
    }
}
