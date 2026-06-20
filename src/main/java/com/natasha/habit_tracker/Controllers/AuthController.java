package com.natasha.habit_tracker.Controllers;

import com.natasha.habit_tracker.DTO.LoginRequest;
import com.natasha.habit_tracker.DTO.LoginResponse;
import com.natasha.habit_tracker.DTO.RegisterRequest;
import com.natasha.habit_tracker.DTO.RegisterResponse;
import com.natasha.habit_tracker.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/api/auth/register")
    public RegisterResponse register(@RequestBody RegisterRequest request) {

        return userService.register(request);
    }

    @PostMapping("/api/auth/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }
}
