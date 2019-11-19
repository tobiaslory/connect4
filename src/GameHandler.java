import java.util.Scanner;

class GameHandler {
    private final GameBoard board;
    private final Player player_one;
    private final Player player_two;
    private boolean player_one_turn;

    GameHandler(boolean versus_ai) {
        board = new GameBoard(7, 6);
        player_one = new Player(false, "Player 1");
        player_two = new Player(versus_ai, "Player 2");
        player_one_turn = true;
    }

    boolean isNotOver() {
        return board.isNotOver();
    }

    void makeMove(Scanner input_reader) throws Exception {
        if (player_one_turn) {
            board.makeMove(true, player_one.getMove(input_reader));
        } else {
            board.makeMove(false, player_two.getMove(input_reader));
        }
        player_one_turn = !player_one_turn;
    }

    String getWinner() throws Exception {
        if (isNotOver())
            throw new Exception("Game is not over yet.");
        if (board.isFull())
            return "Board is full. Game ends in Tie.";
        boolean player_one_won = board.winnerPlayerOne();
        if (player_one_won)
            return "Game is Over. Winner is Player 1.";
        else
            return "Game is Over. Winner is Player 2.";
    }

    @Override
    public String toString() {
        return board.toString();
    }
}
