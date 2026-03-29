package com.spk.internship_management_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spk.internship_management_system.model.InternshipApplication;
public interface InternshipRepository extends JpaRepository<InternshipApplication, Long> {
    
}

