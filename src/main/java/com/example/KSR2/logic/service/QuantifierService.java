package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.Quantifier;
import com.example.KSR2.logic.repository.QuantifierRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Getter
@Service
public class QuantifierService {
    private QuantifierRepository quantifierRepository;

    @Autowired
    public QuantifierService(QuantifierRepository quantifierRepository) {
        this.quantifierRepository = quantifierRepository;
    }

    public List<Quantifier> getQuantifiers() {
        return quantifierRepository.getQuantifiers();
    }
}
