package com.natasha.habit_tracker.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class HabitResponse {

    private String name;

    private Long id;

    private String description;
    private int target;
    private LocalDateTime createdAt;
}


