package com.natasha.habit_tracker.Controllers;

import com.natasha.habit_tracker.DTO.HabitStatsResponse;
import com.natasha.habit_tracker.Services.StatsService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/api/habits/{id}/stats")
    public HabitStatsResponse getStats(@PathVariable long id) {

        return statsService.getHabitStats(id);

    }
}
