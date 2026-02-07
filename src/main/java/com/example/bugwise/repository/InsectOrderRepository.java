package com.example.bugwise.repository;

import com.example.bugwise.entity.InsectOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsectOrderRepository extends JpaRepository<InsectOrder,Long> {
}
