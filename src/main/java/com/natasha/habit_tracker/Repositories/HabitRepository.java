package com.natasha.habit_tracker.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.natasha.habit_tracker.Models.*;


public interface HabitRepository extends JpaRepository<Habit, Long> {
}
