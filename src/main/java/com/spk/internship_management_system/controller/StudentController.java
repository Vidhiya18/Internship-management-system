package com.spk.internship_management_system.controller;

import com.spk.internship_management_system.model.Student;
import com.spk.internship_management_system.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")

public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Add student (POST)
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    // Get all students (GET)
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    // Update student (PUT)
    @PutMapping("/{id}")
public ResponseEntity<?> updateStudent(@PathVariable("id") Long id,
                                       @RequestBody Student student) {
    Optional<Student> optionalStudent = studentRepository.findById(id);

    if (optionalStudent.isPresent()) {
        Student existingStudent = optionalStudent.get();
        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setPassword(student.getPassword());
        studentRepository.save(existingStudent);
        return ResponseEntity.ok(existingStudent);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Student with ID " + id + " not found");
    }
}

    // Delete student (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found");
        }
    }
}


    