package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

class Player {
    private final boolean isAi;
    private final String name;
    private Random move;

    Player(boolean isAi, String name) {
        this.isAi = isAi;
        this.name = name;
        try {
            move = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    int getMove(Scanner inputReader) {
        if (isAi)
            return getMoveAi();
        else
            return getMoveHuman(inputReader);
    }

    private int getMoveAi() {
        return move.nextInt(7);
    }

    private int getMoveHuman(Scanner inputReader) {
        System.out.println(name + ": Enter Column:");
        return inputReader.nextInt() - 1;
    }
}
