package com.pedro.todoListAPI.miscelaneous.exceptions;

public class LoginAlreadyInUseException extends RuntimeException {
    public LoginAlreadyInUseException( ) {
        super("The inserted email is already used by other user.");
    }
}
