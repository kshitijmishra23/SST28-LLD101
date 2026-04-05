package com.example.snakeladder;

/**
 * Controls game difficulty.
 *
 * EASY  — normal fair dice (1-6 uniform).
 * HARD  — crooked dice that only rolls even numbers (2, 4, 6),
 *          making it harder to land on exact cells.
 */
public enum DifficultyLevel {
    EASY,
    HARD
}
