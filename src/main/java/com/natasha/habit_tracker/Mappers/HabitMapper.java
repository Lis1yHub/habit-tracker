package com.natasha.habit_tracker.Mappers;

import com.natasha.habit_tracker.DTO.HabitRequest;
import com.natasha.habit_tracker.DTO.HabitResponse;
import com.natasha.habit_tracker.DTO.*;
import com.natasha.habit_tracker.Models.Habit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {

    public HabitResponse toResponse(Habit habit) {

        HabitResponse response = new HabitResponse();

        response.setName(habit.getName());
        response.setId(habit.getId());
        response.setDescription(habit.getDescription());
        response.setTarget(habit.getTarget());
        response.setCreatedAt(habit.getCreatedAt());

        return response;
    }

    public Habit toEntity(HabitRequest request) {

        Habit habit = new Habit();

        habit.setName(request.getName());
        habit.setDescription(request.getDescription());
        habit.setTarget(request.getTarget());

        return habit;
    }

    public void updateEntity(Habit existingHabit, HabitRequest request) {

        existingHabit.setName(request.getName());
        existingHabit.setDescription(request.getDescription());
        existingHabit.setTarget(request.getTarget());
    }
}
