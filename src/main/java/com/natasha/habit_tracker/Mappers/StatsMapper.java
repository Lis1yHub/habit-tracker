package com.natasha.habit_tracker.Mappers;

import com.natasha.habit_tracker.DTO.*;
import com.natasha.habit_tracker.Models.Habit;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class StatsMapper {

    public HabitStatsResponse toResponse(int currentStreak,
                                         int bestStreak,
                                         double completionRate,
                                         int totalCompletions) {

        HabitStatsResponse response = new HabitStatsResponse();

        response.setCurrentStreak(currentStreak);
        response.setBestStreak(bestStreak);
        response.setCompletionRate(completionRate);
        response.setTotalCompletions(totalCompletions);

        return response;
    }

    public DailyHabitResponse toDailyHabit(Habit habit, boolean completed) {

        DailyHabitResponse habitResponse = new DailyHabitResponse();

        habitResponse.setId(habit.getId());
        habitResponse.setName(habit.getName());
        habitResponse.setCompleted(completed);

        return habitResponse;
    }

    public DailyStatsResponse toDaily(LocalDate date, List<DailyHabitResponse> habits) {
        DailyStatsResponse statsResponse = new DailyStatsResponse();
        statsResponse.setDate(date);
        statsResponse.setHabits(habits);
        return statsResponse;
    }

    public WeekDayStatsResponse toWeekDay(LocalDate date, long totalCount, long completedCount) {

        WeekDayStatsResponse weekStatsResponse = new WeekDayStatsResponse();

        weekStatsResponse.setDate(date);
        weekStatsResponse.setTotalCount(totalCount);
        weekStatsResponse.setCompletedCount(completedCount);

        return weekStatsResponse;
    }

    public WeekStatsResponse toWeek(List<WeekDayStatsResponse> week) {

        WeekStatsResponse weekStatsResponse = new WeekStatsResponse();
        weekStatsResponse.setWeek(week);

        return weekStatsResponse;
    }
}
