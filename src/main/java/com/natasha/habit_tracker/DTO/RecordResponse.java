package com.natasha.habit_tracker.DTO;

import java.time.LocalDateTime;

public class RecordResponse {

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
// добавить проверки на null и пустые зн-я