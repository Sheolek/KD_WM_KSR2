package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.Quantifier;
import com.example.KSR2.logic.repository.QuantifierRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
public class QuantifierService {
    private QuantifierRepository quantifierRepository;

    public List<Quantifier> getQuantifiers() {
        return quantifierRepository.getQuantifiers();
    }
}
