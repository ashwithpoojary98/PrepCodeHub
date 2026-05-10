package io.github.ashwith.systemdesign.snakeladder.board;

public interface IBoard {

    int getCurrentPosition(int currentPosition,int diceTurnedValue);

    int getBoardSize();
}
