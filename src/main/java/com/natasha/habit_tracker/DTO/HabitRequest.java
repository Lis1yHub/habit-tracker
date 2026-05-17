package com.natasha.habit_tracker.DTO;

public class HabitRequest {

    private String name;
    private String description;
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

    public String setName() {
        return name;
    }

    public String setDescription() {
        return description;
    }

    public int setTarget() {
        return target;
    }
}

// добавить проверки на null и пустые зн-я
