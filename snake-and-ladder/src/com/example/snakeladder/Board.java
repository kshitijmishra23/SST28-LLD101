package com.example.snakeladder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The game board — knows its size and which cells have entities (snakes/ladders).
 *
 * Board does NOT know about dice or players — it only answers the question:
 * "if someone lands on cell X, where do they actually end up?"
 * This keeps it single-responsibility and easy to test.
 */
public class Board {
    private final int size;
    private final int winningCell;
    private final Map<Integer, BoardEntity> entityMap;

    public Board(int size, List<BoardEntity> entities) {
        this.size = size;
        this.winningCell = size * size;

        Map<Integer, BoardEntity> map = new HashMap<>();
        for (BoardEntity entity : entities) {
            map.put(entity.getTriggerCell(), entity);
        }
        this.entityMap = Collections.unmodifiableMap(map);
    }

    public int getWinningCell() { return winningCell; }

    /**
     * Resolves the final position after a player moves.
     * Returns the same position if the move overshoots the board.
     */
    public int resolvePosition(int current, int diceRoll) {
        int landed = current + diceRoll;

        if (landed > winningCell) {
            return current;
        }

        BoardEntity entity = entityMap.get(landed);
        if (entity != null) {
            System.out.println("  " + entity.label() + " at " + landed
                    + "! Moving to " + entity.getDestinationCell());
            return entity.getDestinationCell();
        }

        return landed;
    }

    /** Checks if a cell is already taken by an entity (used during board generation). */
    public boolean isCellOccupied(int cell) {
        return entityMap.containsKey(cell);
    }

    public void printLayout() {
        System.out.println("Board: " + size + "x" + size + " (cells 1 to " + winningCell + ")");
        for (BoardEntity entity : entityMap.values()) {
            System.out.println("  " + entity);
        }
    }
}
