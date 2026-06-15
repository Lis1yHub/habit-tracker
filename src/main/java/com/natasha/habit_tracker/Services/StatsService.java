package com.natasha.habit_tracker.Services;

import com.natasha.habit_tracker.DTO.*;
import com.natasha.habit_tracker.Exceptions.HabitNotFoundException;
import com.natasha.habit_tracker.Repositories.HabitRepository;
import com.natasha.habit_tracker.Repositories.RecordRepository;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Calculator.*;
import com.natasha.habit_tracker.Repositories.projections.RecordCountByDateView;
import com.natasha.habit_tracker.Repositories.projections.RecordDateView;
import com.natasha.habit_tracker.Mappers.StatsMapper;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final HabitRepository habitRepository;
    private final RecordRepository recordRepository;
    private final StreakCalculator streakCalculator;
    private final StatsMapper statsMapper;

    public StatsService(HabitRepository habitRepository, RecordRepository recordRepository, StreakCalculator streakCalculator, StatsMapper statsMapper) {
        this.habitRepository = habitRepository;
        this.recordRepository = recordRepository;
        this.streakCalculator = streakCalculator;
        this.statsMapper = statsMapper;
    }

    // вернуть статистику привычки
    @Transactional(readOnly = true)
    public HabitStatsResponse getHabitStats(Long id) {

        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        // отсортированный по дате (от старой к новой)
        List<LocalDate> datesAsc = recordRepository.findByHabitIdOrderByDateAsc(id)
                .stream()
                .map(RecordDateView::getDate)
                .toList();

        int currentStreak = streakCalculator.calculateCurrentStreak(datesAsc);
        int bestStreak = streakCalculator.calculateBestStreak(datesAsc);
        double completionRate = streakCalculator.calculateCompletionRate(datesAsc, habit.getCreatedAt());
        int totalCompletions = streakCalculator.calculateTotalCompletions(datesAsc);

        return statsMapper.toResponse(
                currentStreak,
                bestStreak,
                completionRate,
                totalCompletions
        );
    }

    // вернуть дневную статистику привычки
    public DailyStatsResponse getDailyHabitStats() {

        List<Habit> habits = habitRepository.findAll();
        LocalDate date = LocalDate.now();

        List<DailyHabitResponse> habitsList = habits
                .stream()
                .map(habit -> {

                    boolean completed = recordRepository.existsByHabitIdAndDate(habit.getId(), date);

                    return statsMapper.toDailyHabit(habit, completed);
                })
                .toList();

        return statsMapper.toDaily(date, habitsList);
    }

    public WeekStatsResponse getWeekStats() {

        long totalCount = habitRepository.count();
        LocalDate today = LocalDate.now();
        LocalDate startDate = today.minusDays(6);

        List<RecordCountByDateView> counts = recordRepository.findCompletedCountsBetween(startDate, today);

        Map<LocalDate, Long> countsByDate = counts.stream()
                .collect(Collectors.toMap(
                        RecordCountByDateView::getDate,
                        RecordCountByDateView::getCompletedCount
                ));

        List<WeekDayStatsResponse> week = IntStream.range(0, 7)
                .mapToObj(i -> {
                    LocalDate date = today.minusDays(i);

                    long completedCount = countsByDate.getOrDefault(date, 0L);

                    return statsMapper.toWeekDay(date, totalCount, completedCount);
                })
                .toList();

        return statsMapper.toWeek(week);
    }
}
