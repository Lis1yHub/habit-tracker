package com.natasha.habit_tracker.Mappers;

import com.natasha.habit_tracker.DTO.RecordResponse;
import com.natasha.habit_tracker.Models.Record;
import org.springframework.stereotype.Component;

@Component
public class RecordMapper {

    public RecordResponse toResponse(Record record) {

        RecordResponse response = new RecordResponse();

        response.setHabitId(record.getHabit().getId());
        response.setRecordId(record.getId());
        response.setDate(record.getDate());

        return response;
    }
}
