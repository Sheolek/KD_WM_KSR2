package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.LinguisticVariable;
import com.example.KSR2.logic.repository.LinguisticVariableRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Getter
@Service
public class LinguisticVariableService {
    LinguisticVariableRepository linguisticVariableRepository;

    @Autowired
    public LinguisticVariableService(LinguisticVariableRepository linguisticVariableRepository) {
        this.linguisticVariableRepository = linguisticVariableRepository;
    }

    public List<LinguisticVariable> getVariables() {
        return linguisticVariableRepository.getVariables();
    }
}
