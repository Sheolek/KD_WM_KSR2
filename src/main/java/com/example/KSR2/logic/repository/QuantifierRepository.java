package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.Quantifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuantifierRepository extends JpaRepository<Quantifier, Long> {
}
