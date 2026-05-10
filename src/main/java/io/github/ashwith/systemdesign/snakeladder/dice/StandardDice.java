package io.github.ashwith.systemdesign.snakeladder.dice;

import java.util.Random;

public class StandardDice implements IRoller {

    private final int diceStartNumber;
    private final int diceEndNumber;
    private final Random rand;

    public StandardDice(int diceStartNumber, int diceEndNumber) {
        this.diceStartNumber = diceStartNumber;
        this.diceEndNumber = diceEndNumber;
        rand = new Random();
    }


    @Override
    public int turnDice() {
        return rand.nextInt(this.diceStartNumber , this.diceEndNumber +1);
    }
}
