package com.pedro.todoListAPI.miscelaneous.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("The request token is invalid.");
    }
}
