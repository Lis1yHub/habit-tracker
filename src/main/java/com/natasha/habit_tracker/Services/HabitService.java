package com.natasha.habit_tracker.Services;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.natasha.habit_tracker.DTO.HabitRequest;
import com.natasha.habit_tracker.Mappers.HabitMapper;
import com.natasha.habit_tracker.Models.Habit;
import org.springframework.stereotype.Service;
import com.natasha.habit_tracker.Exceptions.*;

@Service
public class HabitService {

    private final HabitMapper habitMapper;

    public HabitService(HabitMapper habitMapper) {
        this.habitMapper = habitMapper;
    }

    Map<Long, Habit> habits = new HashMap<>();

    public Habit createHabit(HabitRequest request) {

        Habit habit = habitMapper.toEntity(request);
        habits.put(habit.getHabitId(), habit);

        return habit;
    }

    public Habit getHabitById(long id) {

        Habit habit = habits.get(id);

        if (habit == null) {
            throw new HabitNotFoundException("Habit not found");
        }

        return habit;
    }

    public List<Habit> getAllHabits() {
        return new ArrayList<>(habits.values());
    }

    public Habit updateHabit(long id, HabitRequest request) {

        Habit updatedHabit = habits.get(id);

        if (updatedHabit == null) {
            throw new HabitNotFoundException("Habit not found");
        }

        habitMapper.updateEntity(updatedHabit, request);

        return updatedHabit;
    }

    public void deleteHabit(long id) {

        if (!habits.containsKey(id)) {
            throw new HabitNotFoundException("Habit not found");
        }
        habits.remove(id);
    }
}