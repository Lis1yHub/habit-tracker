package com.natasha.habit_tracker.Services;

import com.natasha.habit_tracker.DTO.HabitRequest;
import com.natasha.habit_tracker.Exceptions.HabitNotFoundException;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Repositories.HabitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class HabitServiceTests {

    @Autowired
    private HabitService habitService;

    @Autowired
    private HabitRepository habitRepository;

    private HabitRequest testHabitRequest;

    @BeforeEach
    void setUp() {
        testHabitRequest = new HabitRequest();
        testHabitRequest.setName("Пить воду");
        testHabitRequest.setDescription("Пить 2 литра воды каждый день");
        testHabitRequest.setTarget(8);
    }

    @Test
    void testCreateHabit() {
        Habit savedHabit = habitService.createHabit(testHabitRequest);

        assertNotNull(savedHabit.getId(), "ID привычки не должен быть null");

        Habit foundHabit = habitRepository.findById(savedHabit.getId()).orElse(null);

        assertNotNull(foundHabit);
        assertEquals("Пить воду", foundHabit.getName(), "Имя должно совпадать");
        assertEquals("Пить 2 литра воды каждый день", foundHabit.getDescription(), "Описание должно совпадать");
        assertEquals(8, foundHabit.getTarget(), "Цель должна совпадать");
    }

    @Test
    void testGetHabitById_Found() {
        Habit savedHabit = habitService.createHabit(testHabitRequest);

        Long habitId = savedHabit.getId();

        Habit foundHabit = habitService.getHabitById(habitId);

        assertNotNull(foundHabit);
        assertEquals(savedHabit.getId(), foundHabit.getId(), "ID привычки не должен быть null");
        assertEquals(savedHabit.getName(), foundHabit.getName(), "Имя должно совпадать");
    }

    @Test
    void testGetHabitById_NotFound() {
        Long id = 1919191919L;
        assertThrows(HabitNotFoundException.class, () -> habitService.getHabitById(id));
    }
}
