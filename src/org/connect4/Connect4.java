package org.connect4;

import java.util.Scanner;

class Connect4 {
    public static void main(String[] args) {
        boolean endGame = false;
        Scanner inputReader = new Scanner(System.in);

        System.out.println("Welcome to connect 4.");
        while (!endGame) {
            boolean versusAi = isVersus_ai(inputReader);

            GameHandler game = new GameHandler(versusAi);
            while (game.isNotOver()) {
                try {
                    System.out.println(game.toString());
                    game.makeMove(inputReader);
                } catch (GameBoard.InvalidMoveException e) {
                    System.out.println(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                System.out.println(game.toString());
                String winner = game.getWinner();
                System.out.println(winner);
            } catch (InvalidStateException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }

            endGame = isEnd_game(inputReader);

        }
    }

    private static boolean isEnd_game(Scanner input_reader) {
        boolean endGame = false;
        boolean playAgain = false;
        while (!playAgain) {
            System.out.println("Start a new round?(y/n):");
            String answer = input_reader.next();
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

    private static boolean isVersus_ai(Scanner input_reader) {
        boolean playersSet = false;
        boolean versusAi = false;
        while (!playersSet) {
            System.out.println("Enter number of human players(1-2):");
            int numberOfPlayers = input_reader.nextInt();
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
