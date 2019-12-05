package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class GameHandler {
    private final GameBoard board;
    private final Player playerOne;
    private final Player playerTwo;
    private boolean playerOneTurn;

    GameHandler(boolean versusAi) throws NoSuchAlgorithmException {
        board = new GameBoard(7, 6);
        playerOne = new Player(false, "Player 1");
        playerTwo = new Player(versusAi, "Player 2");
        playerOneTurn = true;
    }

    boolean isNotOver() {
        return board.isNotOver();
    }

    void makeMove(Scanner inputReader) throws GameBoard.InvalidMoveException {
        if (playerOneTurn) {
            board.makeMove(true, playerOne.getMove(inputReader));
        } else {
            board.makeMove(false, playerTwo.getMove(inputReader));
        }
        playerOneTurn = !playerOneTurn;
    }

    String getWinner() throws InvalidStateException {
        if (isNotOver())
            throw new InvalidStateException("Game is not over yet.");
        if (board.isFull())
            return "Board is full. Game ends in Tie.";
        boolean playerOneWon = board.winnerPlayerOne();
        if (playerOneWon)
            return "Game is Over. Winner is Player 1.";
        else
            return "Game is Over. Winner is Player 2.";
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
