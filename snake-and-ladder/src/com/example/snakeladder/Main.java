package com.example.snakeladder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point — collects board size, player names, and dice type,
 * then kicks off the game.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- board setup ---
        System.out.print("Board size (n for n×n): ");
        int n = sc.nextInt();

        // --- players ---
        System.out.print("Number of players: ");
        int count = sc.nextInt();

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            System.out.print("Player " + i + " name: ");
            players.add(new Player(sc.next()));
        }

        // --- difficulty selection (strategy pattern in action) ---
        System.out.print("Difficulty level (easy / hard): ");
        String diffInput = sc.next().trim().toUpperCase();
        DifficultyLevel difficulty = DifficultyLevel.valueOf(diffInput);
        Dice dice = buildDice(difficulty);

        // --- wire everything together and play ---
        Board board = BoardFactory.create(n);
        GameEngine engine = new GameEngine(board, dice, players);

        System.out.println("\n=== Game Start ===\n");
        engine.start();

        sc.close();
    }

    private static Dice buildDice(DifficultyLevel level) {
        switch (level) {
            case HARD: return new CrookedDice();
            default:   return new NormalDice(6);
        }
    }
}
