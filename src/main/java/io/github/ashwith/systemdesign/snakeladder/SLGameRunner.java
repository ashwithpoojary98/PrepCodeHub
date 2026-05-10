package io.github.ashwith.systemdesign.snakeladder;

import io.github.ashwith.systemdesign.snakeladder.board.BoardBuilder;
import io.github.ashwith.systemdesign.snakeladder.board.IBoard;
import io.github.ashwith.systemdesign.snakeladder.dice.IRoller;
import io.github.ashwith.systemdesign.snakeladder.dice.StandardDice;
import io.github.ashwith.systemdesign.snakeladder.game.SLGame;
import io.github.ashwith.systemdesign.snakeladder.model.Player;

import java.util.ArrayList;
import java.util.List;

public class SLGameRunner {

    public static void main(String[] args) {

        IBoard board = new BoardBuilder()
                .boardSize(100)
                .addLadder(1, 10)
                .addLadder(20, 45)
                .addLadder(37, 81)
                .addSnake(72, 54)
                .addSnake(46, 22)
                .addSnake(18, 2)
                .build();

        List<Player> players = new ArrayList<>();
        Player p1 = new Player("Virat");
        Player p2 = new Player("Rohith");
        Player p3 = new Player("Rahul");
        players.add(p1);
        players.add(p2);
        players.add(p3);

        IRoller dice = new StandardDice(1, 6);

        SLGame slGame = new SLGame(board, players, dice);
        slGame.startGame();


    }
}
