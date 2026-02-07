package com.example.bugwise.repository;

import com.example.bugwise.entity.Habitat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat,Long> {
}
