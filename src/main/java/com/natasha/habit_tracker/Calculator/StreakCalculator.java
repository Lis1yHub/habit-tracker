package com.natasha.habit_tracker.Calculator;

import com.natasha.habit_tracker.Models.Record;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.temporal.ChronoUnit;

@Service
public class StreakCalculator {

    // общее количество выполнений
    public int calculateTotalCompletions(List<Record> records) {

        return records.size();
    }

    // текущая серия побед
    public int calculateCurrentStreak(List<Record> records) {
        int currentStreak = 0;
        LocalDate expectedDate = LocalDate.now();

        for (Record record: records) {
            if (record.getDate().equals(expectedDate)) {
                currentStreak++;
                expectedDate = expectedDate.minusDays(1);
            } else {
                break;
            }
        }

        return currentStreak;
    }

    public double calculateCompletionRate(List<Record> records, LocalDateTime habitCreatedAt) {
        LocalDate today = LocalDate.now();
        LocalDate createdDate = habitCreatedAt.toLocalDate();

        Set<LocalDate> set = new HashSet<>();
        for (Record record: records) {
            set.add(record.getDate());
        }

        int completedDays = set.size();
        long N = ChronoUnit.DAYS.between(createdDate, today) + 1;

        return (completedDays * 100.0) / N;
    }

    public int calculateBestStreak(List<Record> records) {

        if (records == null || records.isEmpty()) {
            return 0;
        }

        int currentStreak = 1;
        int bestStreak = 1;
        LocalDate prevDate = records.get(0).getDate();

        for (int i = 1; i < records.size(); i++) {

            LocalDate currentDate = records.get(i).getDate();

            if (currentDate.equals(prevDate.plusDays(1))) {
                currentStreak++;
            } else {
                bestStreak = Math.max(bestStreak, currentStreak);
                currentStreak = 1;
            }

            prevDate = currentDate;
        }

        bestStreak = Math.max(bestStreak, currentStreak);

        return bestStreak;
    }
}
