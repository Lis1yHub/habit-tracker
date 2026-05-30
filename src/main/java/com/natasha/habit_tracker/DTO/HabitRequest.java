package com.natasha.habit_tracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HabitRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Target must be a positive number")
    private int target;
}
