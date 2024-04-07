package com.quenzvezda.todolistjavaspring.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("Username or Password is incorrect.");
    }
}
