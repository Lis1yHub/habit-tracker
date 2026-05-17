package com.natasha.habit_tracker.Models;
import java.time.LocalDateTime;

//@Entity
public class Record {

    private long recordId;
    private long habitId;
    private LocalDateTime createdAt;

    public long getRecordId() {
        return recordId;
    }

    public long getHabitId() {
        return habitId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public void setHabitId(long habitId) {
        this.habitId = habitId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
