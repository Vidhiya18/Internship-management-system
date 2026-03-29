package com.spk.internship_management_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spk.internship_management_system.model.Task;
public interface TaskRepository extends JpaRepository<Task, Long> {
}
