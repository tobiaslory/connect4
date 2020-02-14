package org.connect4;

/**
 * This class represents the game board containing the game pieces.
 *
 * @author Tobias Lory
 */
public class GameBoard {
    /**
     * The number of columns of the board.
     */
    private final int width;
    /**
     * The number of rows of the board.
     */
    private final int height;
    /**
     * A matrix storing the state of each game tile.
     */
    private final int[][] field;
    /**
     * The number of pieces in a line required for winning.
     */
    private final int piecesForWin;
    /**
     * Whether the last executed move was done by player one or two.
     */
    private boolean lastMovePlayerOne;
    /**
     * The column of the last move.
     */
    private int lastMoveCol;
    /**
     * The row of the last move.
     */
    private int lastMoveRow;

    /**
     * Creates a new game board with the specified dimensions.
     *
     * @param width        the number of columns.
     * @param height       the number of rows.
     * @param piecesForWin The number of pieces in a line required for winning.
     */
    public GameBoard(int width, int height, int piecesForWin) {
        this.width = width;
        this.height = height;
        this.piecesForWin = piecesForWin;
        field = new int[this.height][this.width];
        lastMovePlayerOne = false;
    }

    /**
     * Determines whether one player has achieved victory
     * or if the game is over in a draw.
     *
     * @return true if the game is still ongoing, otherwise false.
     */
    public boolean isNotOver() {
        if (isFull())
            return false;
        return !winVertical() && !winHorizontal() && !winDiagonalSlash() && !winDiagonalBackslash();
    }

    /**
     * Check if the last move created of a line of pieces
     * in a Northeast-Southwest direction
     *
     * @return true if a the found line is long enough for victory otherwise false.
     */
    private boolean winDiagonalSlash() {
        int connected = 1;
        for (int i = lastMoveRow + 1, j = lastMoveCol + 1; i < height && j < width; i++, j++)
            if (field[i][j] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        for (int i = lastMoveRow - 1, j = lastMoveCol - 1; i >= 0 && j >= 0; i--, j--)
            if (field[i][j] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        return connected >= piecesForWin;
    }

    /**
     * Check if the last move created of a line of pieces
     * in a Northwest-Southeast direction
     *
     * @return true if a the found line is long enough for victory otherwise false.
     */
    private boolean winDiagonalBackslash() {
        int connected = 1;
        for (int i = lastMoveRow + 1, j = lastMoveCol - 1; i < height && j >= 0; i++, j--)
            if (field[i][j] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        for (int i = lastMoveRow - 1, j = lastMoveCol + 1; i >= 0 && j < width; i--, j++)
            if (field[i][j] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        return connected >= piecesForWin;
    }

    /**
     * Check if there are enough pieces belonging to this player directly below
     * the last move to suffice for victory.
     *
     * @return true if a the found line is long enough for victory otherwise false.
     */
    private boolean winVertical() {
        int connected = 1;
        for (int i = lastMoveRow - 1; i >= 0; i--)
            if (field[i][lastMoveCol] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        return connected >= piecesForWin;
    }

    /**
     * Check if there are enough pieces belonging to this player directly
     * to the left and right of the last move to suffice for victory.
     *
     * @return true if a the found line is long enough for victory otherwise false.
     */
    private boolean winHorizontal() {
        int connected = 1;
        for (int i = lastMoveCol - 1; i >= 0; i--)
            if (field[lastMoveRow][i] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        for (int i = lastMoveCol + 1; i < width; i++)
            if (field[lastMoveRow][i] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        return connected >= piecesForWin;
    }

    /**
     * Execute the move for the selected player in the selected column,
     * that means flipping the bottommost unassigned game tile in that column.
     *
     * @param playerOne true if it is player one making the move, false for player two.
     * @param move      The ID of the column in which to make a move.
     * @throws InvalidMoveException The move could not be made because the column
     *                              does not exist or is already full.
     */
    public void makeMove(boolean playerOne, int move) throws InvalidMoveException {
        if (move < 0 || move >= width)
            throw new InvalidMoveException("Invalid move. Column not valid.");
        boolean moveMade = false;
        for (int i = 0; i < height; i++)
            if (field[i][move] == 0) {
                field[i][move] = playerOne ? 1 : 2;
                lastMoveRow = i;
                moveMade = true;
                break;
            }
        if (!moveMade)
            throw new InvalidMoveException("Invalid move. Column already full.");
        lastMovePlayerOne = playerOne;
        lastMoveCol = move;
    }

    /**
     * Check whether the whole board has been filled without achieving victory.
     * Thus the game ends in a draw.
     *
     * @return true if the game board has no more unassigned tiles.
     */
    public boolean isFull() {
        for (int i = 0; i < width; i++)
            if (field[height - 1][i] == 0)
                return false;
        return true;
    }

    /**
     * Check which player is victorious
     *
     * @return True if player one has won, false for player two.
     * @throws InvalidStateException The game ended in tie or is not over yet.
     */
    public boolean winnerPlayerOne() throws InvalidStateException {
        if (isFull() || isNotOver())
            throw new InvalidStateException("Game did not end decisively");
        return lastMovePlayerOne;
    }

    /**
     * Generate a String representation of the game board
     * consisting of '_' for empty tiles.
     * 'x' for player one and 'o' for player two.
     *
     * @return Representation of the game board.
     */
    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++)
                if (field[i][j] == 0)
                    display.append("_ ");
                else
                    display.append(field[i][j] == 1 ? "x " : "o ");
            display.append("\n");
        }
        for (int i = 1; i <= width; i++)
            display.append(i).append(" ");
        return display.toString();


    }

    /**
     * Custom Exception for Illegal moves selected by players.
     */
    public static class InvalidMoveException extends Exception {
        /**
         * Initializes this Exception.
         *
         * @param s message for the exception.
         */
        public InvalidMoveException(String s) {
            super(s);
        }
    }
}
