package com.natasha.habit_tracker.Calculator;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StreakCalculatorTests {

    private final StreakCalculator streakCalculator = new StreakCalculator();

    @Test
    void calculateCurrentStreak_ShouldReturnThree() {

        LocalDate today = LocalDate.now();

        List<LocalDate> dates = List.of(
                today.minusDays(2),
                today.minusDays(1),
                today
        );

        int result = streakCalculator.calculateCurrentStreak(dates);

        assertEquals(3, result);
    }

    @Test
    void calculateCurrentStreak_ShouldBreakOnMissingDay() {

        LocalDate today = LocalDate.now();

        List<LocalDate> dates = List.of(
                today.minusDays(2),
                today
        );

        int result = streakCalculator.calculateCurrentStreak(dates);

        assertEquals(1, result);
    }

    @Test
    void calculateBestStreak_ShouldReturnLongestSeries() {

        LocalDate base = LocalDate.of(2026, 1, 1);

        List<LocalDate> dates = List.of(
                base,
                base.plusDays(1),
                base.plusDays(2),

                base.plusDays(6),
                base.plusDays(7),

                base.plusDays(9),
                base.plusDays(10),
                base.plusDays(11),
                base.plusDays(12)
        );

        int result = streakCalculator.calculateBestStreak(dates);

        assertEquals(4, result);
    }

    @Test
    void calculateCompletionRate_ShouldCalculateCorrectly() {

        LocalDate today = LocalDate.now();
        LocalDateTime createdAt = today.minusDays(5).atStartOfDay();

        List<LocalDate> dates = List.of(
                today.minusDays(4),
                today.minusDays(2),
                today
        );

        double result = streakCalculator.calculateCompletionRate(dates, createdAt);

        assertEquals(50.0, result);
    }

    @Test
    void calculateBestStreak_EmptyList_ShouldReturnZero() {

        List<LocalDate> dates = List.of();

        int result = streakCalculator.calculateBestStreak(dates);

        assertEquals(0, result);
    }
}
