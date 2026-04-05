package com.example.snakeladder;

/**
 * A ladder on the board — boosts the player up from bottom to top.
 */
public class Ladder implements BoardEntity {
    private final int bottom;
    private final int top;

    public Ladder(int bottom, int top) {
        if (top <= bottom) throw new IllegalArgumentException("Ladder top must be above its bottom");
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public int getTriggerCell() { return bottom; }

    @Override
    public int getDestinationCell() { return top; }

    @Override
    public String label() { return "Ladder"; }

    @Override
    public String toString() {
        return "Ladder{" + bottom + " -> " + top + "}";
    }
}
