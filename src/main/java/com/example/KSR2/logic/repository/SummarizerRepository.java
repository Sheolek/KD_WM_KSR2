package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SummarizerRepository extends JpaRepository<Label, Long> {
}
