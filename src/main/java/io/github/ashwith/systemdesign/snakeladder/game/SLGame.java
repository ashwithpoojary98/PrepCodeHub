package io.github.ashwith.systemdesign.snakeladder.game;

import io.github.ashwith.systemdesign.snakeladder.board.Board;
import io.github.ashwith.systemdesign.snakeladder.board.IBoard;
import io.github.ashwith.systemdesign.snakeladder.dice.IRoller;
import io.github.ashwith.systemdesign.snakeladder.model.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SLGame {

    private final IBoard board;
    private final Queue<Player> playerList;
    private final IRoller roller;
    private GameStatus gameStatus;

    public SLGame(IBoard board, List<Player> player, IRoller roller) {
        this.board = board;
        this.playerList = new LinkedList<>(player);
        this.roller = roller;
        this.gameStatus = GameStatus.IN_PROGRESS;
    }

    public void startGame() {
        System.out.println("Starting game...");
        while (gameStatus.equals(GameStatus.IN_PROGRESS)) {
            Player player = playerList.poll();
            if (player == null) {
                gameStatus = GameStatus.FINISHED;
                break;
            }
            turnCurrentPlayer(player);
            System.out.println("Turn: " + player.getName() + " " + player.getCurrentPosition());
            if (gameStatus.equals(GameStatus.FINISHED)) {
                System.out.println("Player " + player.getName() + " won!");
                break;
            } else {
                playerList.add(player);
            }

        }

    }

    private void turnCurrentPlayer(Player player) {
        int dice = roller.turnDice();
        int playerPosition = player.getCurrentPosition();
        int finalPosition = board.getCurrentPosition(playerPosition, dice);
        if (finalPosition == board.getBoardSize() || finalPosition < 0) {
            gameStatus = GameStatus.FINISHED;
        }
        player.setCurrentPosition(finalPosition);
    }

}
