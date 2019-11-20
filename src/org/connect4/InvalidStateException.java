package org.connect4;

class InvalidStateException extends Throwable {
    InvalidStateException(String message) {
        super(message);
    }
}
