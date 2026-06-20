package com.natasha.habit_tracker.Controllers;

import com.natasha.habit_tracker.DTO.DailyStatsResponse;
import com.natasha.habit_tracker.DTO.HabitStatsResponse;
import com.natasha.habit_tracker.DTO.WeekStatsResponse;
import com.natasha.habit_tracker.Services.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;


    @GetMapping("/api/habits/{id}/stats")
    public HabitStatsResponse getStats(@PathVariable long id) {

        return statsService.getHabitStats(id);
    }

    @GetMapping("/api/stats/daily")
    public DailyStatsResponse getDailyStats() {

        return statsService.getDailyHabitStats();
    }

    @GetMapping("/api/stats/week")
    public WeekStatsResponse getWeekStats() {

        return statsService.getWeekStats();
    }
}
