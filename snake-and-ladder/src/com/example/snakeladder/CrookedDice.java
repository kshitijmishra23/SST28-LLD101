package com.example.snakeladder;

import java.util.Random;

/**
 * A rigged dice that only lands on even numbers (2, 4, 6).
 *
 * Demonstrates OCP — GameEngine works with any Dice implementation.
 * Swapping NormalDice for CrookedDice changes game behaviour without
 * modifying a single line in GameEngine.
 */
public class CrookedDice implements Dice {
    private static final int[] EVEN_FACES = {2, 4, 6};
    private final Random random;

    public CrookedDice() {
        this.random = new Random();
    }

    @Override
    public int roll() {
        return EVEN_FACES[random.nextInt(EVEN_FACES.length)];
    }

    @Override
    public int faces() { return 6; }
}
