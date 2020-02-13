package org.connect4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GameHandlerTest {

    private static final Logger log = Logger.getLogger(GameHandlerTest.class.getName());
    private GameHandler testHandler;

    @BeforeEach
    void setUp() {
        try {
            testHandler = new GameHandler(false);
        } catch (NoSuchAlgorithmException e) {
            log.severe(e.getMessage());
        }
    }

    @AfterEach
    void tearDown() {
        testHandler = null;
    }

    @Test
    void isNotOver() {
        assertTrue(testHandler.isNotOver());
    }

    @Test
    void getWinner() {
        assertThrows(InvalidStateException.class, () -> testHandler.getWinner());
        Scanner inputReader = new Scanner(
                "1 2 3 4 5 6 7 " +
                        "1 2 3 4 5 6 7 " +
                        "1 2 3 4 5 6 7 " +
                        "2 3 4 5 6 7 1 " +
                        "1 2 3 4 5 6 7 " +
                        "1 2 3 4 5 6 7 " +
                        "1 2 3 4 5 6 7");
        try {
            for (int i = 0; i < 42; ++i)
                testHandler.makeMove(inputReader);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertDoesNotThrow(() -> testHandler.getWinner());
        try {
            assertEquals("Board is full. Game ends in Tie.", testHandler.getWinner());
        } catch (InvalidStateException e) {
            log.severe(e.getMessage());
        }
    }

    @Test
    void winnerOne() {
        Scanner inputReader = new Scanner("4 5 4 5 4 5 4");
        try {
            for (int i = 0; i < 7; ++i)
                testHandler.makeMove(inputReader);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertDoesNotThrow(() -> testHandler.getWinner());
        try {
            assertEquals("Game is Over. Winner is Player 1.", testHandler.getWinner());
        } catch (InvalidStateException e) {
            log.severe(e.getMessage());
        }
    }

    @Test
    void winnerTwo() {
        Scanner inputReader = new Scanner("3 4 5 4 5 4 5 4");
        try {
            for (int i = 0; i < 8; ++i)
                testHandler.makeMove(inputReader);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertDoesNotThrow(() -> testHandler.getWinner());
        try {
            assertEquals("Game is Over. Winner is Player 2.", testHandler.getWinner());
        } catch (InvalidStateException e) {
            log.severe(e.getMessage());
        }
    }

    @Test
    void testToString() {
        String emptyLine = "_ _ _ _ _ _ _ \n";
        String emptyBoard = emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                "1 2 3 4 5 6 7 ";
        assertEquals(emptyBoard, testHandler.toString());
    }
}