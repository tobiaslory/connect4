import java.util.Random;
import java.util.Scanner;

class Player {
    private final boolean is_ai;
    private final String name;

    Player(boolean is_ai, String name) {
        this.is_ai = is_ai;
        this.name = name;
    }

    int getMove(Scanner input_reader) {
        if (is_ai)
            return getMoveAi();
        else
            return getMoveHuman(input_reader);
    }

    private int getMoveAi() {
        Random move = new Random();
        return move.nextInt(7);
    }

    private int getMoveHuman(Scanner input_reader) {
        System.out.println(name + ": Enter Column:");
        return input_reader.nextInt() - 1;
    }
}
