package com.pedro.todoListAPI.miscelaneous.exceptions;

public class TermNotFoundException extends RuntimeException {
    public TermNotFoundException(String term) {
        super("Term: " + term + ", does not lead to any results.");
    }
}
