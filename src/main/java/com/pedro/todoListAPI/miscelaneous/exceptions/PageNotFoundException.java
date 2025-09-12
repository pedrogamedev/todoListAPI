package com.pedro.todoListAPI.miscelaneous.exceptions;

public class PageNotFoundException extends RuntimeException {
    public PageNotFoundException(int page) {
        super("Page with number "+ page +" not found.");
    }
}
