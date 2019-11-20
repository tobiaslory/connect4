package org.connect4;

class GameBoard {
    private final int width;
    private final int height;
    private final int[][] field;
    private boolean lastMovePlayerOne;
    private int lastMoveCol;
    private int lastMoveRow;

    GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        field = new int[this.height][this.width];
        lastMovePlayerOne = false;
    }

    boolean isNotOver() {
        if (isFull())
            return false;
        return !winVertical() && !winHorizontal() && !winDiagonalSlash() && !winDiagonalBackslash();
    }

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
        return connected >= 4;
    }

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
        return connected >= 4;
    }

    private boolean winVertical() {
        int connected = 1;
        for (int i = lastMoveRow - 1; i >= 0; i--)
            if (field[i][lastMoveCol] == (lastMovePlayerOne ? 1 : 2))
                connected++;
            else
                break;
        return connected >= 4;
    }

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
        return connected >= 4;
    }

    void makeMove(boolean playerOne, int move) throws InvalidMoveException {
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

    boolean isFull() {
        for (int i = 0; i < width; i++)
            if (field[height - 1][i] == 0)
                return false;
        return true;
    }

    boolean winnerPlayerOne() throws InvalidStateException {
        if (isFull() || isNotOver())
            throw new InvalidStateException("Game did not end decisively");
        return lastMovePlayerOne;
    }

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

    static class InvalidMoveException extends Throwable {
        InvalidMoveException(String s) {
            super(s);
        }
    }
}
