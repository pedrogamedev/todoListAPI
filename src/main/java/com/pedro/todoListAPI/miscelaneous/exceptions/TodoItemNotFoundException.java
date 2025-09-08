package com.pedro.todoListAPI.miscelaneous.exceptions;

public class TodoItemNotFoundException extends RuntimeException {
    public TodoItemNotFoundException(Long id) {
        super("BlogPost not found with id: "+ id + ".");
    }
}
