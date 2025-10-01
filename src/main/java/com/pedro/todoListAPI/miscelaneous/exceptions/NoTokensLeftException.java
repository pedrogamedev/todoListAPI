package com.pedro.todoListAPI.miscelaneous.exceptions;

public class NoTokensLeftException extends RuntimeException {
    public NoTokensLeftException(String message) {
        super(message);
    }
}
