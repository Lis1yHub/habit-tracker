package com.natasha.habit_tracker.Models;

//import jakarta.persistence.Entity;
import java.time.LocalDateTime;

//@Entity
public class Habit {

    private long habitId;
    private String name;
    private String description;
    private int target;
    private LocalDateTime createdAt;

    public long getHabitId() {
        return habitId;
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

    public void setHabitId(long habitId) {
        this.habitId = habitId;
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
