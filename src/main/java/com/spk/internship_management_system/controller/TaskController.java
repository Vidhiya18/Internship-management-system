package com.spk.internship_management_system.controller;
import org.springframework.web.bind.annotation.*;
import com.spk.internship_management_system.model.Task;
import com.spk.internship_management_system.repository.TaskRepository;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    // ASSIGN TASK (Mentor)
    @PostMapping
    public Task assignTask(@RequestBody Task task) {
        task.setStatus("Pending");   // default
        return repo.save(task);
    }

    // VIEW ALL TASKS (Mentor/Admin/Student)
    @GetMapping
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    // UPDATE STATUS (Student)
    @PutMapping("/{id}")
public Task updateTask(@PathVariable("id") Long id, @RequestBody Task t) {

    Task task = repo.findById(id).orElse(null);

    if (task == null) {
        throw new RuntimeException("Task not found");
    }

    task.setStatus(t.getStatus());

    return repo.save(task);
}
}