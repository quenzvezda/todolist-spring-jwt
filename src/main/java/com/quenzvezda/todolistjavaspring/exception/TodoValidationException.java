package com.quenzvezda.todolistjavaspring.exception;

public class TodoValidationException extends RuntimeException {
    public TodoValidationException(String message) {
        super(message);
    }
}
