package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * This class holds the different game elements and provides
 * methods for the game flow.
 *
 * @author Tobias Lory
 */
class GameHandler {
    /**
     * Default game board width.
     */
    private static final int GAME_WIDTH = 7;
    /**
     * Default game board height.
     */
    private static final int GAME_HEIGHT = 6;
    /**
     * Default number of pieces in a line for win.
     */
    private static final int PIECES_FOR_WIN = 4;
    /**
     * Instance of the game board.
     */
    private final GameBoard board;
    /**
     * Player instance for the human player.
     */
    private final Player playerOne;
    /**
     * Player instance for the second player.
     */
    private final Player playerTwo;
    /**
     * Stores current turn information, if true it is player one's turn.
     */
    private boolean playerOneTurn;

    /**
     * Initializes the game board at 7x6 Rectangle and the 2 players.
     *
     * @param versusAi Whether the second player will be controlled by AI or a human
     * @throws NoSuchAlgorithmException Player could not be created because no RNG exists
     */
    GameHandler(boolean versusAi) throws NoSuchAlgorithmException {
        board = new GameBoard(GAME_WIDTH, GAME_HEIGHT, PIECES_FOR_WIN);
        playerOne = new Player(false, "Player 1");
        playerTwo = new Player(versusAi, "Player 2");
        playerOneTurn = true;
    }

    /**
     * Information about game still ongoing.
     *
     * @return true if the game is still going. false if the game is decided.
     */
    boolean isNotOver() {
        return board.isNotOver();
    }

    /**
     * Asks the current player for a move, executes it on the board and flips current player.
     *
     * @param inputReader The Scanner object for receiving player input.
     * @throws GameBoard.InvalidMoveException Input move could not be executed because it is invalid.
     */
    void makeMove(Scanner inputReader) throws GameBoard.InvalidMoveException {
        if (playerOneTurn) {
            board.makeMove(true, playerOne.getMove(inputReader, GAME_WIDTH));
        } else {
            board.makeMove(false, playerTwo.getMove(inputReader, GAME_WIDTH));
        }
        playerOneTurn = !playerOneTurn;
    }

    /**
     * Generates a message about the end of the game depending on the current game state.
     *
     * @return A message containing information about the winner if any.
     * @throws InvalidStateException The game is not over yet.
     */
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

    /**
     * The state of the game is displayed by the game board.
     *
     * @return A representation of the board.
     */
    @Override
    public String toString() {
        return board.toString();
    }
}
