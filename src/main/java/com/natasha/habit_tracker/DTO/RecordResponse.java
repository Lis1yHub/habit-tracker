package com.natasha.habit_tracker.DTO;

import java.time.LocalDateTime;

public class RecordResponse {

    @Id
    private Long recordId;
    private Long habitId;
    private LocalDateTime createdAt;

    public Long getRecordId() {
        return recordId;
    }

    public Long getHabitId() {
        return habitId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public void setHabitId(Long habitId) {
        this.habitId = habitId;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
// добавить проверки на null и пустые зн-я