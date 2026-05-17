package com.natasha.habit_tracker.Controllers;

import com.natasha.habit_tracker.DTO.RecordResponse;
import com.natasha.habit_tracker.Models.Record;
import com.natasha.habit_tracker.Services.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // Получить все записи привычки
    @GetMapping("/api/habits/{id}/records")
    public ResponseEntity<List<RecordResponse>> getRecordsByHabitId(@PathVariable long id) {

        List<Record> habitRecords = recordService.getRecordsByHabitId(id);

        List<RecordResponse> response = new ArrayList<>();

        for (Record record: habitRecords) {
            RecordResponse r = new RecordResponse();

            r.setRecordId(record.getRecordId());
            r.setHabitId(record.getHabitId());
            r.setCreatedAt(record.getCreatedAt());

            response.add(r);
        }

        return ResponseEntity.ok(response);
    }

    // Отметить выполнение за сегодня
    @PostMapping("/api/habits/{habitId}/records")
    public ResponseEntity<RecordResponse> addRecord(@PathVariable long habitId) {

        Record record = recordService.createRecord(habitId);

        RecordResponse response = new RecordResponse();
        response.setHabitId(record.getHabitId());
        response.setRecordId(record.getRecordId());
        response.setCreatedAt(record.getCreatedAt());

        return ResponseEntity.status(201).body(response);
    }

    // удалить запись
    @DeleteMapping("/records/{id}")
    public void deleteRecord(@PathVariable long id) {

    }
}
