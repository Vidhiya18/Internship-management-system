package com.spk.internship_management_system.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.spk.internship_management_system.model.Mentor;
import com.spk.internship_management_system.repository.MentorRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/mentors")
public class MentorController {

    private final MentorRepository repo;

    public MentorController(MentorRepository repo) {
        this.repo = repo;
    }

    // Add mentor
    @PostMapping
    public Mentor create(@RequestBody Mentor mentor) {
        return repo.save(mentor);
    }

    // View mentors
    @GetMapping
    public List<Mentor> getAll() {
        return repo.findAll();
    }
}