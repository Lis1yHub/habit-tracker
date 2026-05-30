package com.natasha.habit_tracker.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(com.natasha.habit_tracker.Exceptions.HabitNotFoundException.class)
    public ResponseEntity<String> HabitNotFoundException(com.natasha.habit_tracker.Exceptions.HabitNotFoundException ex) {

        String message = ex.getMessage();

        return ResponseEntity.status(404).body(message);
    }

    @ExceptionHandler(com.natasha.habit_tracker.Exceptions.RecordNotFoundException.class)
    public ResponseEntity<String> RecordNotFoundException(com.natasha.habit_tracker.Exceptions.RecordNotFoundException ex) {

        String message = ex.getMessage();

        return ResponseEntity.status(404).body(message);
    }
}
