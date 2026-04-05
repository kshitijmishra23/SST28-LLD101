package com.example.snakeladder;

import java.util.Random;

/**
 * Standard fair dice — each face from 1 to N has equal probability.
 */
public class NormalDice implements Dice {
    private final int numFaces;
    private final Random random;

    public NormalDice(int numFaces) {
        if (numFaces < 1) throw new IllegalArgumentException("Dice must have at least 1 face");
        this.numFaces = numFaces;
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(numFaces) + 1;
    }

    @Override
    public int faces() { return numFaces; }
}
