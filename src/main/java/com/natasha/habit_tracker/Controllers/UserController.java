package com.natasha.habit_tracker.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/users")
    public String getUsers() {
        return "all users";
    }

    @PutMapping("/user")
    public String putUsers() {
        return "PUT /users";
    }

    @PostMapping("/user")
    public String postUsers() {
        return "new user";
    }

    @DeleteMapping("/user")
    public void deleteUser() {
        //"DELETE /user";
    }
}
