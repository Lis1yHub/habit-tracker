package com.natasha.habit_tracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WeekStatsResponse {

    private List<WeekDayStatsResponse> week;
}
