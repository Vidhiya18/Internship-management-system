package com.spk.internship_management_system.controller;

import org.springframework.web.bind.annotation.*;
import com.spk.internship_management_system.model.user;
import com.spk.internship_management_system.repository.UserRepository;
import java.util.List;   // ✅ THIS LINE FIXES ERROR

@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // SIGNUP
    @PostMapping("/signup")
    public String signup(@RequestBody user user) {
        repo.save(user);
        return "User Registered Successfully";
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestBody user user) {

        List<user> users = repo.findByEmail(user.getEmail());

        if (users.isEmpty()) {
            return "Invalid Credentials";
        }

        for (user u : users) {
            if (u.getPassword() != null && u.getPassword().equals(user.getPassword())) {
                return "Login Success";
            }
        }

        return "Invalid Credentials";
    }
}