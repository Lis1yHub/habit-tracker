package com.natasha.habit_tracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import com.natasha.habit_tracker.Models.Record;
import java.util.Optional;
import com.natasha.habit_tracker.Models.Habit;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByHabitId(Long habitId);

    boolean existsByHabitIdAndDate(Long habitId, LocalDate date);

    Optional<Record> findByHabitAndDate(Habit habit, LocalDate date);

    List<Record> findByHabitIdOrderByDateDesc(Long habitId);

    List<Record> findByHabitIdOrderByDateAsc(Long habitId);
}
