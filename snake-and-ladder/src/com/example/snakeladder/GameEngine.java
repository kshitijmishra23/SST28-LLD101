package com.example.snakeladder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Drives the game loop — rolls dice, moves players, declares winners.
 *
 * Depends on Board and Dice abstractions (DIP), not on concrete classes.
 * You can swap in a CrookedDice or a custom Board without touching this code.
 */
public class GameEngine {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> turnQueue;
    private final List<Player> finishOrder;

    public GameEngine(Board board, Dice dice, List<Player> players) {
        this.board = board;
        this.dice = dice;
        this.turnQueue = new LinkedList<>(players);
        this.finishOrder = new ArrayList<>();
    }

    /** Runs the full game until only one player remains. */
    public void start() {
        board.printLayout();
        System.out.println();

        while (turnQueue.size() > 1) {
            Player current = turnQueue.poll();
            int rolled = dice.roll();
            int before = current.getPosition();
            int after = board.resolvePosition(before, rolled);
            current.moveTo(after);

            System.out.println(current.getName() + " rolled " + rolled
                    + " : " + before + " -> " + after);

            if (after == board.getWinningCell()) {
                finishOrder.add(current);
                System.out.println("  " + current.getName()
                        + " finished! (rank #" + finishOrder.size() + ")");
            } else {
                turnQueue.add(current);
            }
        }

        // last player standing
        if (!turnQueue.isEmpty()) {
            Player last = turnQueue.poll();
            System.out.println("\n" + last.getName() + " is the last one on the board.");
        }

        printResults();
    }

    private void printResults() {
        System.out.println("\n--- Final Rankings ---");
        for (int i = 0; i < finishOrder.size(); i++) {
            System.out.println("  #" + (i + 1) + " " + finishOrder.get(i).getName());
        }
    }
}
