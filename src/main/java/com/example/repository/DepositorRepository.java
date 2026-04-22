package com.example.repository;

import com.example.entity.Depositor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DepositorRepository extends JpaRepository<Depositor, Integer> {
    List<Depositor> findByCustomerId(Integer customerId);
}