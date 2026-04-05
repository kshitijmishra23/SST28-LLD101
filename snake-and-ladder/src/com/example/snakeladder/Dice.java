package com.example.snakeladder;

/**
 * Strategy interface for rolling a dice.
 *
 * Different dice types produce different distributions — a normal dice
 * gives uniform 1-6, a crooked dice only lands on even numbers, etc.
 * Adding a new dice type means implementing this interface, not changing GameEngine.
 */
public interface Dice {
    int roll();
    int faces();
}
