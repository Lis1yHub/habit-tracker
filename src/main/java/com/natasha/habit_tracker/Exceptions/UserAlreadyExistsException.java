package com.natasha.habit_tracker.Exceptions;

public class UserAlreadyExistsException extends AppException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
