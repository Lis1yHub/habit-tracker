package com.natasha.habit_tracker.Controllers;

import com.natasha.habit_tracker.DTO.RecordResponse;
import com.natasha.habit_tracker.Mappers.RecordMapper;
import com.natasha.habit_tracker.Models.Record;
import com.natasha.habit_tracker.Services.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RecordController {

    private final RecordService recordService;
    private final RecordMapper recordMapper;

    public RecordController(RecordService recordService, RecordMapper recordMapper) {

        this.recordService = recordService;
        this.recordMapper = recordMapper;
    }

    // Получить все записи привычки
    @GetMapping("/api/habits/{id}/records")
    public ResponseEntity<List<RecordResponse>> getRecordsByHabitId(@PathVariable long id) {

        List<Record> habitRecords = recordService.getRecordsByHabitId(id);
        List<RecordResponse> response = new ArrayList<>();

        for (Record record: habitRecords) {

            RecordResponse r = recordMapper.toResponse(record);
            response.add(r);
        }

        return ResponseEntity.ok(response);
    }

    // Отметить выполнение за сегодня
    @PostMapping("/api/habits/{habitId}/records")
    public ResponseEntity<RecordResponse> addRecord(@PathVariable long habitId) {

        Record record = recordService.createRecord(habitId);
        RecordResponse response = recordMapper.toResponse(record);

        return ResponseEntity.status(201).body(response);
    }

    // удалить запись
    @DeleteMapping("/api/records/{recordId}")
    public ResponseEntity<RecordResponse> deleteRecord(@PathVariable long recordId) {

        recordService.deleteRecord(recordId);

        return ResponseEntity.noContent().build();
    }
}
