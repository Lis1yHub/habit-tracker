package com.natasha.habit_tracker.Calculator;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.time.temporal.ChronoUnit;

@Service
public class StreakCalculator {

    // общее количество выполнений
    public int calculateTotalCompletions(List<LocalDate> dates) {

        return dates.size();
    }

    // текущая серия выполнений
    public int calculateCurrentStreak(List<LocalDate> dates) {

        if (dates.isEmpty()) return 0;

        int currentStreak = 0;
        LocalDate expectedDate = LocalDate.now();

        for (int i = dates.size() - 1; i >= 0; i--) {

            if (dates.get(i).equals(expectedDate)) {
                currentStreak++;
                expectedDate = expectedDate.minusDays(1);
            } else {
                break;
            }
        }

        return currentStreak;
    }

    // процент выполнения
    public double calculateCompletionRate(List<LocalDate> dates, LocalDateTime habitCreatedAt) {

        LocalDate today = LocalDate.now();
        LocalDate createdDate = habitCreatedAt.toLocalDate();

        int completedDays = dates.size();
        long N = ChronoUnit.DAYS.between(createdDate, today) + 1;

        return (completedDays * 100.0) / N;
    }

    // лучшая серия выполнений
    public int calculateBestStreak(List<LocalDate> dates) {

        if (dates.isEmpty()) return 0;

        int currentStreak = 1;
        int bestStreak = 1;
        LocalDate prevDate = dates.get(0);

        for (int i = 1; i < dates.size(); i++) {

            LocalDate currentDate = dates.get(i);

            if (currentDate.equals(prevDate.plusDays(1))) {
                currentStreak++;
            } else {
                bestStreak = Math.max(bestStreak, currentStreak);
                currentStreak = 1;
            }

            prevDate = currentDate;
        }

        return Math.max(bestStreak, currentStreak);
    }
}
