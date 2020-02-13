package org.connect4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    private static final Logger log = Logger.getLogger(PlayerTest.class.getName());
    private Player testPlayerOne, testPlayerTwo;

    @BeforeEach
    void setUp() {
        try {
            testPlayerOne = new Player(false, "Player 1");
            testPlayerTwo = new Player(true, "Player 2");
        } catch (NoSuchAlgorithmException e) {
            log.severe(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        testPlayerOne = null;
        testPlayerTwo = null;
    }

    @Test
    void getMove() {
        Scanner inputReader = new Scanner("4");
        int moveOne = testPlayerOne.getMove(inputReader, 7);
        assertEquals(3, moveOne);
        int moveTwo = testPlayerTwo.getMove(inputReader, 7);
        assertTrue(moveTwo >= 0 && moveTwo < 7);
    }
}