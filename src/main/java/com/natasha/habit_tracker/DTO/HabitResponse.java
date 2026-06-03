package com.natasha.habit_tracker.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
public class HabitResponse {

    private String name;

    private Long id;

    private String description;

    private int target;

    private LocalDateTime createdAt;
}


