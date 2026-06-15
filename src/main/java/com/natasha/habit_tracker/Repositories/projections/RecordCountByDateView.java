package com.natasha.habit_tracker.Repositories.projections;

import java.time.LocalDate;

public interface RecordCountByDateView {

    LocalDate getDate();

    long getCompletedCount();

}
