package io.github.ashwith.systemdesign.snakeladder.model;

import io.github.ashwith.systemdesign.snakeladder.exceptions.BoardCreationException;

public class Ladder {

    private final int lowerEnd;
    private final int upperEnd;

    public Ladder(int lowerEnd, int upperEnd) {

        if(lowerEnd > upperEnd){
            throw new BoardCreationException(String.format("Ladder %s lower bound with greater than %s upper bound.", lowerEnd, upperEnd));
        }
        this.lowerEnd = lowerEnd;
        this.upperEnd = upperEnd;
    }

    public int getLowerEnd() {
        return lowerEnd;
    }
    public int getUpperEnd() {
        return upperEnd;
    }
}
