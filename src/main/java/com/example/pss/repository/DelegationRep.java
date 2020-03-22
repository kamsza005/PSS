package com.example.pss.repository;

import com.example.pss.model.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DelegationRep extends JpaRepository<Delegation, Long> {
    List<Delegation> findAllByUserId(Long id);
    List<Delegation> findByOrderByDateTimeStartDesc();
    List<Delegation> findAllByUserIdOrderByDateTimeStartDesc(Long userId);
}
