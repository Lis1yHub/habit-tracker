package com.natasha.habit_tracker.Models;

//import jakarta.persistence.Entity;
import java.time.LocalDateTime;

//@Entity
public class Habit {

    private long id;
    private String name;
    private String description;
    private int target;
    private LocalDateTime createdAt;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
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

    public void setId(long id) {
        this.id = id;
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
