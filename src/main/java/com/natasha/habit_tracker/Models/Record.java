package com.natasha.habit_tracker.Models;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "habit_id")
    private Habit habit;

    @PrePersist
    public void createdDate() {
        if (date == null){
            this.date = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }
}
