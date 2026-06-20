package com.natasha.habit_tracker.Controllers;

import com.natasha.habit_tracker.Mappers.HabitMapper;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Services.HabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.natasha.habit_tracker.DTO.HabitRequest;
import com.natasha.habit_tracker.DTO.HabitResponse;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class HabitController {

    private final HabitService habitService;
    private final HabitMapper habitMapper;


    // Получить привычку по ID
    @GetMapping("/api/habits/{id}")
    public ResponseEntity<HabitResponse> getHabit(@PathVariable long id) {

        Habit habit = habitService.getHabitById(id);
        HabitResponse response = habitMapper.toResponse(habit);

        return ResponseEntity.ok(response);
    }

    // Получить все привычки
    @GetMapping("/api/habits")
    public ResponseEntity<List<HabitResponse>> getAllHabits() {

        List<Habit> habits = habitService.getAllHabits();

        List<HabitResponse> response = new ArrayList<>();
        for (Habit habit: habits) {

            HabitResponse r = habitMapper.toResponse(habit);
            response.add(r);
        }

        return ResponseEntity.ok(response);
    }

    // Обновить привычку
    @PutMapping("/api/habits/{id}")
    public ResponseEntity<HabitResponse> updateHabit(@PathVariable long id,
                                                     @Valid @RequestBody HabitRequest request) {

        Habit updatedHabit = habitService.updateHabit(id, request);
        HabitResponse response = habitMapper.toResponse(updatedHabit);

        return ResponseEntity.ok(response);
    }

    // Создать привычку
    @PostMapping("/api/habits")
    public ResponseEntity<HabitResponse> createHabit(@Valid @RequestBody HabitRequest request) {

        Habit habit = habitService.createHabit(request);
        HabitResponse response = habitMapper.toResponse(habit);

        return ResponseEntity.status(201).body(response);
    }

    // Удалить привычку
    @DeleteMapping("/api/habits/{id}")
    public ResponseEntity<HabitResponse> deleteHabit(@PathVariable long id) {

        habitService.deleteHabit(id);

        return ResponseEntity.noContent().build();
    }
}
