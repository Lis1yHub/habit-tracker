package com.natasha.habit_tracker.DTO;

import java.time.LocalDateTime;

public class HabitResponse {

    private String name;
    private long id;
    private String description;
    private int target;
    private LocalDateTime createdAt;

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getTarget() {
        return target;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}

// добавить проверки на null и пустые зн-я

