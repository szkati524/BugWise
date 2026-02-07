package com.example.bugwise.repository;

import com.example.bugwise.entity.InsectFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsectFamilyRepository extends JpaRepository<InsectFamily,Long> {
}
