package com.natasha.habit_tracker.Mappers;

import com.natasha.habit_tracker.DTO.HabitStatsResponse;
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
}
