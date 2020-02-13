package org.connect4;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represents a player and is responsible for generating move information.
 *
 * @author Tobias Lory
 */
class Player {
    /**
     * is this player controlled by an AI or human.
     */
    private final boolean isAi;
    /**
     * String name representation for the player.
     */
    private final String name;
    /**
     * Random number generator for AI move calculation.
     */
    private Random move;

    /**
     * Creates a player instance.
     *
     * @param isAi true if this player should be controlled by ai
     *             false if by human
     * @param name A string representation for this player
     * @throws NoSuchAlgorithmException The RNG could not be created
     */
    Player(boolean isAi, String name) throws NoSuchAlgorithmException {
        this.isAi = isAi;
        this.name = name;
        move = SecureRandom.getInstanceStrong();
    }

    /**
     * Ask the player for a move and return it.
     *
     * @param inputReader The Scanner object for receiving player input.
     * @param boardWidth  the number of columns to choose from.
     * @return The move the player wants to make.
     */
    int getMove(Scanner inputReader, int boardWidth) {
        if (isAi)
            return getMoveAi(boardWidth);
        else
            return getMoveHuman(inputReader);
    }

    /**
     * Get the move for an AI player, currently just any random column.
     *
     * @param boardWidth The number of columns to choose from.
     * @return the ID of the column in which to make a move.
     */
    private int getMoveAi(int boardWidth) {
        return move.nextInt(boardWidth);
    }

    /**
     * Ask the human player for the move they want to make.
     *
     * @param inputReader The Scanner object for receiving player input.
     * @return the ID of the column in which to make a move.
     */
    private int getMoveHuman(Scanner inputReader) {
        System.out.println(name + ": Enter Column:");
        return inputReader.nextInt() - 1;
    }
}
