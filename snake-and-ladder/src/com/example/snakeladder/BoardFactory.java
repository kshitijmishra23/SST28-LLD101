package com.example.snakeladder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Factory that builds a Board with randomly placed snakes and ladders.
 *
 * Encapsulates the messy random-generation logic so that Board itself
 * stays clean and focused on movement rules.
 */
public class BoardFactory {

    private BoardFactory() { }  // utility class, no instances

    /**
     * Creates a board of the given size with a balanced number of snakes and ladders.
     * The entity count scales with board size so small boards aren't overcrowded.
     */
    public static Board create(int size) {
        int maxCell = size * size;
        int entityCount = size;
        Random rng = new Random();

        Set<Integer> reserved = new HashSet<>();
        reserved.add(1);          // starting zone
        reserved.add(maxCell);    // winning cell

        List<BoardEntity> entities = new ArrayList<>();

        // place snakes — head above tail
        for (int i = 0; i < entityCount; i++) {
            int head = pickUnused(rng, reserved, 2, maxCell - 1);
            reserved.add(head);
            int tail = pickUnused(rng, reserved, 1, head - 1);
            reserved.add(tail);
            entities.add(new Snake(head, tail));
        }

        // place ladders — bottom below top
        for (int i = 0; i < entityCount; i++) {
            int bottom = pickUnused(rng, reserved, 2, maxCell - 1);
            reserved.add(bottom);
            int top = pickUnused(rng, reserved, bottom + 1, maxCell - 1);
            reserved.add(top);
            entities.add(new Ladder(bottom, top));
        }

        return new Board(size, entities);
    }

    private static int pickUnused(Random rng, Set<Integer> used, int min, int max) {
        List<Integer> pool = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            if (!used.contains(i)) pool.add(i);
        }
        return pool.get(rng.nextInt(pool.size()));
    }
}
