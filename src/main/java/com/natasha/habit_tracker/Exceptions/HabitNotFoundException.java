package com.natasha.habit_tracker.Exceptions;

public class HabitNotFoundException extends AppException {
    public HabitNotFoundException(String message) {
        super(message);
    }
}