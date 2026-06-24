package com.bajaj.bfhl.exception;

/**
 * Custom exception thrown when request input is invalid or cannot be processed.
 */
public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String message) {
        super(message);
    }
}
