package com.natasha.habit_tracker.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DailyHabitResponse {

    private Long id;

    private String name;

    private boolean completed;
}
