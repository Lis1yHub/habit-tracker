package com.natasha.habit_tracker.Repositories;

import com.natasha.habit_tracker.Models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

    List<Habit> findByUser_Id(Long userId);
}
