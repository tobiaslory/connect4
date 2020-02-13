package org.connect4;

public class InvalidStateException extends Exception {
    InvalidStateException(String message) {
        super(message);
    }
}
