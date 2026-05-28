package com.natasha.habit_tracker.DTO;

import java.time.LocalDateTime;

public class HabitResponse {

    private String name;

    @Id
    private Long id;

    private String description;
    private int target;
    private LocalDateTime createdAt;

    public String getName() {
        return name;
    }

    public Long getId() {
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

    public void setId(Long id) {
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

