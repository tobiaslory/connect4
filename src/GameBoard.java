class GameBoard {
    private final int width;
    private final int height;
    private final int[][] field;
    private boolean last_move_player_one;
    private int last_move_col;
    private int last_move_row;

    GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
        field = new int[this.height][this.width];
        last_move_player_one = false;
    }

    boolean isNotOver() {
        if (isFull())
            return false;
        return !winVertical() && !winHorizontal() && !winDiagonal();
    }

    private boolean winDiagonal() {
        int connected = 1;
        for (int i = last_move_row + 1, j = last_move_col + 1; i < height && j < width; i++, j++)
            if (field[i][j] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        for (int i = last_move_row - 1, j = last_move_col - 1; i >= 0 && j >= 0; i--, j--)
            if (field[i][j] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        if (connected >= 4)
            return true;
        connected = 1;
        for (int i = last_move_row + 1, j = last_move_col - 1; i < height && j >= 0; i++, j--)
            if (field[i][j] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        for (int i = last_move_row - 1, j = last_move_col + 1; i >= 0 && j < width; i--, j++)
            if (field[i][j] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        return connected >= 4;
    }

    private boolean winVertical() {
        int connected = 1;
        for (int i = last_move_row - 1; i >= 0; i--)
            if (field[i][last_move_col] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        return connected >= 4;
    }

    private boolean winHorizontal() {
        int connected = 1;
        for (int i = last_move_col - 1; i >= 0; i--)
            if (field[last_move_row][i] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        for (int i = last_move_col + 1; i < width; i++)
            if (field[last_move_row][i] == (last_move_player_one ? 1 : 2))
                connected++;
            else
                break;
        return connected >= 4;
    }

    void makeMove(boolean player_one, int move) throws Exception {
        if (move < 0 || move >= width)
            throw new Exception("Invalid move. Column not valid.");
        boolean move_made = false;
        for (int i = 0; i < height; i++)
            if (field[i][move] == 0) {
                field[i][move] = player_one ? 1 : 2;
                last_move_row = i;
                move_made = true;
                break;
            }
        if (!move_made)
            throw new Exception("Invalid move. Column already full.");
        last_move_player_one = player_one;
        last_move_col = move;
    }

    boolean isFull() {
        for (int i = 0; i < width; i++)
            if (field[height - 1][i] == 0)
                return false;
        return true;
    }

    boolean winnerPlayerOne() throws Exception {
        if (isFull() || isNotOver())
            throw new Exception("Game did not end decisively");
        return last_move_player_one;
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
}
