package com.spk.internship_management_system.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spk.internship_management_system.model.user;
import java.util.List;

public interface UserRepository extends JpaRepository<user, Long> {

    // find all users with same email (to avoid error)
    List<user> findByEmail(String email);

}