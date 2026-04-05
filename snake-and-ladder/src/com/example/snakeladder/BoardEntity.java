package com.example.snakeladder;

/**
 * Abstraction for anything that redirects a player on the board.
 *
 * Both snakes and ladders share the same contract — they intercept a cell
 * and relocate the player elsewhere. New entity types (e.g. portals, power-ups)
 * can be added by implementing this interface without touching Board or GameEngine.
 */
public interface BoardEntity {
    int getTriggerCell();
    int getDestinationCell();
    String label();
}
