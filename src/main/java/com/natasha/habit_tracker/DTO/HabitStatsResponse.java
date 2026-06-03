package com.natasha.habit_tracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HabitStatsResponse {

    private int currentStreak;

    private int bestStreak;

    private double completionRate;

    private int totalCompletions;

    public int getTotalComplections() {
        return totalCompletions;
    }
}
