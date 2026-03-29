package com.spk.internship_management_system.controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.spk.internship_management_system.model.InternshipApplication;
import com.spk.internship_management_system.repository.InternshipRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/internships")
public class InternshipController {

    private final InternshipRepository repo;

    public InternshipController(InternshipRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public InternshipApplication create(@RequestBody InternshipApplication internship) {
        return repo.save(internship);
    }

    @GetMapping
    public List<InternshipApplication> getAll() {
        return repo.findAll();
    }

    //  UPDATE (only change here)
    @PutMapping("/{id}")
    public InternshipApplication update(@PathVariable("id") Long id,
                                        @RequestBody InternshipApplication newData) {

        return repo.findById(id).map(i -> {
            i.setTitle(newData.getTitle());
            i.setDomain(newData.getDomain());
            i.setStartDate(newData.getStartDate());
            i.setEndDate(newData.getEndDate());
            i.setDuration(newData.getDuration());
            i.setDescription(newData.getDescription());
            i.setStudentName(newData.getStudentName());
            i.setEmail(newData.getEmail());
            i.setCollegeName(newData.getCollegeName());
            return repo.save(i);
        }).orElseThrow(() -> new RuntimeException("ID Not Found"));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Deleted Successfully";
        } else {
            return "ID Not Found";
        }
    }
}

