package com.natasha.habit_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HabitTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HabitTrackerApplication.class, args);
	}
}
// ТЕСТЫ ДЛЯ 3 УРОВНЯ КТОРЫЕ ПРОПАЛИ