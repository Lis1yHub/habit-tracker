package com.natasha.habit_tracker.Repositories;

import com.natasha.habit_tracker.Models.Habit;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HabitRepository extends JpaRepository<Habit, Long> {
}
