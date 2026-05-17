package com.natasha.habit_tracker.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HabitNotFoundException.class)
    public ResponseEntity<String> HabitNotFoundException(HabitNotFoundException ex) {

        String message = ex.getMessage();

        return ResponseEntity.status(404).body(message);
    }
}
