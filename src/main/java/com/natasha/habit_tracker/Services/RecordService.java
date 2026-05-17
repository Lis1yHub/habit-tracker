package com.natasha.habit_tracker.Services;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.natasha.habit_tracker.Models.Record;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RecordService {

    Map<Long, Record> records = new HashMap<>();

    // создание записи
    public Record createRecord(long habitId) {

        Record record = new Record();
        record.setHabitId(habitId);
        //record.setRecordId();
        //record.setCreatedAt();

        records.put(record.getRecordId(), record);

        return record;
    }

    // вернуть запись по ID
    public Record getRecordById(long recordId) {
        return records.get(recordId);
    }

    // Получить все записи привычки
    public List<Record> getRecordsByHabitId(long habitId) {

        List<Record> habitRecords = new ArrayList<>();

        for (Record rec: records.values()) {
            if (rec.getHabitId() == habitId) {
                habitRecords.add(rec);
            }
        }

        return habitRecords;
    }

    // удалить запись
    public void deleteRecord(long id) {
        records.remove(id);
    }
}
