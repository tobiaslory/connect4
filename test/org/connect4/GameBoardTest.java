package org.connect4;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {
    private static final Logger log = Logger.getLogger(GameBoardTest.class.getName());
    private GameBoard testBoard;

    @BeforeEach
    void setUp() {
        testBoard = new GameBoard(7, 6, 4);
    }

    @AfterEach
    void tearDown() {
        testBoard = null;
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        String emptyLine = "_ _ _ _ _ _ _ \n";
        String emptyBoard =  emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                "1 2 3 4 5 6 7 ";
        assertEquals(emptyBoard, testBoard.toString());
        try {
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 2);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        String afterOneRound = emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                emptyLine +
                "_ _ o x _ _ _ \n" +
                "1 2 3 4 5 6 7 ";
        assertEquals(afterOneRound, testBoard.toString());
    }

    @org.junit.jupiter.api.Test
    void makeMove() {
        assertThrows(GameBoard.InvalidMoveException.class, () -> testBoard.makeMove(true, 7));
        assertThrows(GameBoard.InvalidMoveException.class, () -> testBoard.makeMove(true, -1));
        assertDoesNotThrow(() -> testBoard.makeMove(true, 3));
        try {
            testBoard.makeMove(false, 3);
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 3);
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 3);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertThrows(GameBoard.InvalidMoveException.class, () -> testBoard.makeMove(true, 3));
    }

    @org.junit.jupiter.api.Test
    void isNotOver() {
        assertTrue(testBoard.isNotOver());
        setupWinPlayerOne();
        assertFalse(testBoard.isNotOver());
    }

    @org.junit.jupiter.api.Test
    void OverFull() {
        try {
            for (int i = 0; i < 7; ++i) {
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
            }
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertFalse(testBoard.isNotOver());
    }

    @org.junit.jupiter.api.Test
    void winHorizontal() {
        try {
            testBoard.makeMove(true, 0);
            testBoard.makeMove(false, 0);
            testBoard.makeMove(true, 1);
            testBoard.makeMove(false, 1);
            testBoard.makeMove(true, 2);
            testBoard.makeMove(false, 2);
            testBoard.makeMove(true, 3);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertFalse(testBoard.isNotOver());
    }

    @org.junit.jupiter.api.Test
    void winSlash() {
        try {
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 4);
            testBoard.makeMove(true, 4);
            testBoard.makeMove(false, 5);
            testBoard.makeMove(true, 5);
            testBoard.makeMove(false, 6);
            testBoard.makeMove(true, 5);
            testBoard.makeMove(false, 6);
            testBoard.makeMove(true, 6);
            testBoard.makeMove(false, 2);
            testBoard.makeMove(true, 6);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertFalse(testBoard.isNotOver());
    }

    @org.junit.jupiter.api.Test
    void winBackslash() {
        try {
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 2);
            testBoard.makeMove(true, 2);
            testBoard.makeMove(false, 1);
            testBoard.makeMove(true, 1);
            testBoard.makeMove(false, 0);
            testBoard.makeMove(true, 1);
            testBoard.makeMove(false, 0);
            testBoard.makeMove(true, 0);
            testBoard.makeMove(false, 4);
            testBoard.makeMove(true, 0);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertFalse(testBoard.isNotOver());
    }

    @org.junit.jupiter.api.Test
    void isFull() {
        assertFalse(testBoard.isFull());
        try {
            for (int i = 0; i < 7; ++i) {
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
            }
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertTrue(testBoard.isFull());
    }

    @org.junit.jupiter.api.Test
    void winnerPlayerOne() {
        assertThrows(InvalidStateException.class, () -> testBoard.winnerPlayerOne());
        setupWinPlayerOne();
        assertDoesNotThrow(() -> testBoard.winnerPlayerOne());
        try {
            assertTrue(testBoard.winnerPlayerOne());
        } catch (InvalidStateException e) {
            log.severe(e.getMessage());
        }
    }

    @org.junit.jupiter.api.Test
    void winnerFull() {
        try {
            for (int i = 0; i < 7; ++i) {
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
                testBoard.makeMove(true, i);
                testBoard.makeMove(false, i);
            }
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
        assertThrows(InvalidStateException.class, () -> testBoard.winnerPlayerOne());
    }

    private void setupWinPlayerOne() {
        try {
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 0);
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 0);
            testBoard.makeMove(true, 3);
            testBoard.makeMove(false, 0);
            testBoard.makeMove(true, 3);
        } catch (GameBoard.InvalidMoveException e) {
            log.severe(e.getMessage());
        }
    }
}