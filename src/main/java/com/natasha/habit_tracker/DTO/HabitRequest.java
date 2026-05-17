package com.natasha.habit_tracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class HabitRequest {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @Positive(message = "Target must be a positive number")
    private int target;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getTarget() {
        return target;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}

// добавить проверки на null и пустые зн-я
