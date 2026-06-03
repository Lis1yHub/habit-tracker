package com.natasha.habit_tracker.Services;

import com.natasha.habit_tracker.DTO.HabitStatsResponse;
import com.natasha.habit_tracker.Exceptions.HabitNotFoundException;
import com.natasha.habit_tracker.Models.Record;
import com.natasha.habit_tracker.Repositories.HabitRepository;
import com.natasha.habit_tracker.Repositories.RecordRepository;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Calculator.*;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class StatsService {

    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;
    private final StreakCalculator streakCalculator;

    public StatsService(HabitRepository habitRepository, RecordRepository recordRepository, StreakCalculator streakCalculator) {
        this.habitRepository = habitRepository;
        this.recordRepository = recordRepository;
        this.streakCalculator = streakCalculator;
    }

    // вернуть статистику привычки
    //@Transactional(readOnly = true)
    public HabitStatsResponse getHabitStats(Long id) {

        Optional<Habit> optionalHabit = habitRepository.findById(id);
        Habit habit = optionalHabit.orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        // отсортированный по дате (от старой к новой)
        List<Record> recordsDesc = recordRepository.findByHabitIdOrderByDateDesc(id);
        // отсортированный по дате (от новой к старой)
        List<Record> recordsAsc = recordRepository.findByHabitIdOrderByDateAsc(id);

        // заполнение HabitStatsResponse
        HabitStatsResponse habitStatsResponse = new HabitStatsResponse();
        habitStatsResponse.setTotalCompletions(streakCalculator.calculateTotalCompletions(recordsAsc));
        habitStatsResponse.setCurrentStreak(streakCalculator.calculateCurrentStreak(recordsAsc));
        habitStatsResponse.setCompletionRate(streakCalculator.calculateCompletionRate(recordsAsc, habit.getCreatedAt()));
        habitStatsResponse.setBestStreak(streakCalculator.calculateBestStreak(recordsDesc));

        return habitStatsResponse;
    }
}
