package com.natasha.habit_tracker.Services;

import java.util.*;

import com.natasha.habit_tracker.DTO.HabitRequest;
import com.natasha.habit_tracker.Mappers.HabitMapper;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Repositories.HabitRepository;
import org.springframework.stereotype.Service;
import com.natasha.habit_tracker.Exceptions.*;

@Service
public class HabitService {

    private final HabitMapper habitMapper;
    private final HabitRepository habitRepository;

    public HabitService(HabitMapper habitMapper, HabitRepository habitRepository) {
        this.habitMapper = habitMapper;
        this.habitRepository = habitRepository;
    }

    // создание привычки
    public Habit createHabit(HabitRequest request) {

        Habit habit = habitMapper.toEntity(request);
        return habitRepository.save(habit);
    }

    // вернуть привычку по ID
    public Habit getHabitById(long id) {

        Optional<Habit> optionalHabit = habitRepository.findById(id);

        return optionalHabit.orElseThrow(() -> new HabitNotFoundException("Habit not found"));
    }

    // вернуть все привычки
    public List<Habit> getAllHabits() {

        return habitRepository.findAll();
    }

    // изменить привычку по ID
    public Habit updateHabit(long id, HabitRequest request) {

        Optional<Habit> optionalHabit = habitRepository.findById(id);
        Habit habit = optionalHabit.orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        habitMapper.updateEntity(habit, request);

        return habitRepository.save(habit);
    }

    // удалить привычку
    public void deleteHabit(long id) {

        habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException("Habit not found"));
        habitRepository.deleteById(id);
    }
}