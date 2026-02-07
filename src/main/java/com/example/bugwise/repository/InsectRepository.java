package com.example.bugwise.repository;

import com.example.bugwise.entity.Insect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsectRepository extends JpaRepository<Insect,Long> {
}
