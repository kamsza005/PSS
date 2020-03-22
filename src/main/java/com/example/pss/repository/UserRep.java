package com.example.pss.repository;

import com.example.pss.model.Role;
import com.example.pss.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRep extends JpaRepository<User, Long> {
    List<User> findAllByRole(Role role);

}
