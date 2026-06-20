package com.natasha.habit_tracker.Exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HabitNotFoundException.class)
    public ResponseEntity<String> HabitNotFoundException(HabitNotFoundException ex) {

        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<String> RecordNotFoundException(RecordNotFoundException ex) {

        return ResponseEntity.status(404).body(ex.getMessage());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> InvalidPasswordException(InvalidPasswordException ex) {

        return ResponseEntity.status(401).body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> UserAlreadyExistsException(UserAlreadyExistsException ex) {

        return ResponseEntity.status(409).body(ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> AccessDeniedException(AccessDeniedException ex) {

        return ResponseEntity.status(403).body(ex.getMessage());
    }
}
