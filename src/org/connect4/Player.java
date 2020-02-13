package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

class Player {
    private final boolean isAi;
    private final String name;
    private Random move;

    Player(boolean isAi, String name) throws NoSuchAlgorithmException {
        this.isAi = isAi;
        this.name = name;
        move = SecureRandom.getInstanceStrong();
    }

    int getMove(Scanner inputReader, int boardWidth) {
        if (isAi)
            return getMoveAi(boardWidth);
        else
            return getMoveHuman(inputReader);
    }

    private int getMoveAi(int boardWidth) {
        return move.nextInt(boardWidth);
    }

    private int getMoveHuman(Scanner inputReader) {
        System.out.println(name + ": Enter Column:");
        return inputReader.nextInt() - 1;
    }
}
