package com.spk.internship_management_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spk.internship_management_system.model.Mentor;
public interface MentorRepository extends JpaRepository<Mentor, Long> {}
