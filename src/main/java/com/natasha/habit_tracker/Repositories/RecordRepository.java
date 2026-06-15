package com.natasha.habit_tracker.Repositories;

import com.natasha.habit_tracker.Repositories.projections.RecordCountByDateView;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.time.LocalDate;
import com.natasha.habit_tracker.Models.Record;
import java.util.Optional;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Repositories.projections.RecordDateView;
import org.springframework.data.jpa.repository.Query;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByHabitId(Long habitId);

    boolean existsByHabitIdAndDate(Long habitId, LocalDate date);

    Optional<Record> findByHabitAndDate(Habit habit, LocalDate date);

    List<RecordDateView> findByHabitIdOrderByDateAsc(Long habitId);

    long countByDate(LocalDate date);

    @Query("""
        SELECT
            r.date as date,
            COUNT(r) as completedCount
        FROM Record r
        WHERE r.date BETWEEN :startDate AND :endDate
        GROUP BY r.date
    """)
    List<RecordCountByDateView> findCompletedCountsBetween(LocalDate startDate, LocalDate endDate);

}
