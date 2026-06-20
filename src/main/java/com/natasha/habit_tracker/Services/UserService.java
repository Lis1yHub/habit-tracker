package com.natasha.habit_tracker.Services;

import com.natasha.habit_tracker.DTO.LoginRequest;
import com.natasha.habit_tracker.DTO.LoginResponse;
import com.natasha.habit_tracker.DTO.RegisterRequest;
import com.natasha.habit_tracker.DTO.RegisterResponse;
import com.natasha.habit_tracker.Exceptions.InvalidPasswordException;
import com.natasha.habit_tracker.Exceptions.UserAlreadyExistsException;
import com.natasha.habit_tracker.Models.User;
import com.natasha.habit_tracker.Repositories.UserRepository;
import com.natasha.habit_tracker.Security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public RegisterResponse register(RegisterRequest request) {

        if (userRepository.findByUsername(request.getUsername()).isPresent()) {

            throw new UserAlreadyExistsException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        RegisterResponse response = new RegisterResponse();
        response.setUsername(user.getUsername());

        return response;
    }

    public LoginResponse login(LoginRequest request){

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {

            throw new InvalidPasswordException("Wrong password");
        }

        String token = jwtService.generateToken(user.getUsername());

        LoginResponse response = new LoginResponse();
        response.setUsername(user.getUsername());
        response.setToken(token);

        return response;
    }
}
