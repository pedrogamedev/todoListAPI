package com.pedro.todoListAPI.miscelaneous.exceptions;

public class EmptyDatabaseException extends RuntimeException {
    public EmptyDatabaseException() {
        super("Could not return items because database is empty.");
    }
}
