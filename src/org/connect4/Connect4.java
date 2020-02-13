package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Logger;

class Connect4 {
    private static final Logger log = Logger.getLogger(Connect4.class.getName());

    public static void main(String[] args) {
        boolean endGame = false;
        Scanner inputReader = new Scanner(System.in);

        System.out.println("Welcome to connect 4.");
        while (!endGame) {
            boolean versusAi = isVersusAi(inputReader);

            GameHandler game = null;
            try {
                game = new GameHandler(versusAi);
            } catch (NoSuchAlgorithmException e) {
                log.severe(e.getMessage());
            }
            if (game == null) {
                return;
            }
            while (game.isNotOver()) {
                try {
                    System.out.println(game.toString());
                    game.makeMove(inputReader);
                } catch (GameBoard.InvalidMoveException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    log.severe(e.getMessage());
                }
            }

            try {
                System.out.println(game.toString());
                String winner = game.getWinner();
                System.out.println(winner);
            } catch (InvalidStateException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                log.severe(e.getMessage());
            }

            endGame = isEndGame(inputReader);

        }
    }

    private static boolean isEndGame(Scanner inputReader) {
        boolean endGame = false;
        boolean playAgain = false;
        while (!playAgain) {
            System.out.println("Start a new round?(y/n):");
            String answer = inputReader.next();
            if (answer.equalsIgnoreCase("y")) {
                playAgain = true;
            } else if (answer.equalsIgnoreCase("n")) {
                playAgain = true;
                endGame = true;
            } else {
                System.out.println("Input not recognized. Try again.");
            }
        }
        return endGame;
    }

    private static boolean isVersusAi(Scanner inputReader) {
        boolean playersSet = false;
        boolean versusAi = false;
        while (!playersSet) {
            System.out.println("Enter number of human players(1-2):");
            int numberOfPlayers = inputReader.nextInt();
            if (0 < numberOfPlayers && numberOfPlayers < 3) {
                playersSet = true;
                versusAi = numberOfPlayers == 1;
            } else {
                System.out.println("Number not recognized. Must be between 1 or 2.");
            }
        }
        return versusAi;
    }
}
