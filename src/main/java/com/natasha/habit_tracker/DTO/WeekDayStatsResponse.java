package com.natasha.habit_tracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class WeekDayStatsResponse {

    private LocalDate date;

    private long completedCount;

    private long totalCount;
}
