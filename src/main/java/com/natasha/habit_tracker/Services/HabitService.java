package com.natasha.habit_tracker.Services;

import java.util.*;

import com.natasha.habit_tracker.DTO.HabitRequest;
import com.natasha.habit_tracker.Exceptions.AccessDeniedException;
import com.natasha.habit_tracker.Exceptions.HabitNotFoundException;
import com.natasha.habit_tracker.Mappers.HabitMapper;
import com.natasha.habit_tracker.Models.Habit;
import com.natasha.habit_tracker.Models.User;
import com.natasha.habit_tracker.Repositories.HabitRepository;
import com.natasha.habit_tracker.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitService {

    private final HabitMapper habitMapper;
    private final HabitRepository habitRepository;
    private final UserRepository userRepository;


    public Habit createHabit(HabitRequest request) {

        Habit habit = habitMapper.toEntity(request);

        habit.setUser(getCurrentUser());

        return habitRepository.save(habit);
    }

    public Habit getHabitById(long id) {

        return getUserHabit(id);
    }

    public List<Habit> getAllHabits() {

        return habitRepository.findByUser_Id(getCurrentUser().getId());
    }

    public Habit updateHabit(long id, HabitRequest request) {

        Habit habit = getUserHabit(id);

        habitMapper.updateEntity(habit, request);

        return habitRepository.save(habit);
    }

    public void deleteHabit(long id) {

        Habit habit = getUserHabit(id);

        habitRepository.delete(habit);
    }



    private User getCurrentUser() {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Habit getUserHabit(long id) {

        Habit habit = habitRepository.findById(id)
                .orElseThrow(() -> new HabitNotFoundException("Habit not found"));

        User currentUser = getCurrentUser();

        if (!habit.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("Access denied");
        }

        return habit;
    }
}