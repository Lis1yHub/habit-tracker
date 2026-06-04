package com.natasha.habit_tracker.Models;

import java.time.LocalDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "records",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"habit_id", "date"})
        }
)
@EntityListeners(AuditingEntityListener.class)
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
        if (date == null) {
            this.date = LocalDate.now();
        }
    }
}