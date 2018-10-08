package com.csipon.planningpocker.exception;

public class EntityIsNotExistException extends RuntimeException {
    public EntityIsNotExistException(String message) {
        super(message);
    }

    public EntityIsNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
