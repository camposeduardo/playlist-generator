package com.camposeduardo.generator.exceptions;

public class UserIdInvalidException extends RuntimeException{

    public UserIdInvalidException() {
        super("User ID invalid.");
    }
}
