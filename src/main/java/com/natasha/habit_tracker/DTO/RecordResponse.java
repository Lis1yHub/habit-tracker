package com.natasha.habit_tracker.DTO;

import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecordResponse {

    private Long recordId;
    private Long habitId;
    private LocalDate date;
}