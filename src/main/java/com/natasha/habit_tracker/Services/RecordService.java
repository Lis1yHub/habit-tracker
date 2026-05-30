package com.natasha.habit_tracker.Services;
import java.time.LocalDate;

import com.natasha.habit_tracker.Exceptions.HabitNotFoundException;
import com.natasha.habit_tracker.Exceptions.RecordNotFoundException;
import com.natasha.habit_tracker.Models.Record;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Repositories.RecordRepository;
import com.natasha.habit_tracker.Repositories.HabitRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    private final RecordRepository recordRepository;
    private final HabitRepository habitRepository;

    public RecordService(RecordRepository recordRepository, HabitRepository habitRepository) {
        this.recordRepository = recordRepository;
        this.habitRepository = habitRepository;
    }

    // создание записи
    public Record createRecord(long habitId) {

        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        LocalDate today = LocalDate.now();

        Optional<Record> existingRecord = recordRepository.findByHabitAndDate(habit, today);

        Record record = existingRecord.orElseGet(() -> {
            Record newRecord = new Record();
            newRecord.setHabit(habit);
            return newRecord;
        });

        return recordRepository.save(record);
    }

    // вернуть запись по ID
    public Record getRecordById(long recordId) {

        return recordRepository.findById(recordId)
                .orElseThrow(() -> new RecordNotFoundException("Record not found"));
    }

    // Получить все записи привычки
    public List<Record> getRecordsByHabitId(long habitId) {

        habitRepository.findById(habitId).orElseThrow(() -> new HabitNotFoundException("Habit not found"));
        return recordRepository.findByHabitId(habitId);
    }

    // удалить запись
    public void deleteRecord(long recordId) {

        recordRepository.findById(recordId).orElseThrow(() -> new RecordNotFoundException("Record not found"));

        recordRepository.deleteById(recordId);
    }
}
