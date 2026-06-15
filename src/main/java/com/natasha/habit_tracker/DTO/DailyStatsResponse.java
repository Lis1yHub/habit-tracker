package com.natasha.habit_tracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DailyStatsResponse {

    private LocalDate date;

    private List<DailyHabitResponse> habits;
}
