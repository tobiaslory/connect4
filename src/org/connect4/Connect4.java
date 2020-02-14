package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * @author Tobias Lory
 */
public class Connect4 {
    private static final Logger log = Logger.getLogger(Connect4.class.getName());

    /**
     * Main method
     *
     * @param args Commandline parameters (unused)
     */
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

    /**
     * After the End of a Match this method asks the player if they want to
     * start another Match.
     *
     * @param inputReader The Scanner object for receiving player input.
     * @return true if the player wants the program to exit, false otherwise.
     */
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

    /**
     * Ask the player whether the opponent will be a human or AI.
     *
     * @param inputReader The Scanner object for receiving player input.
     * @return False if player 2 will be controlled by a human, True otherwise.
     */
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
