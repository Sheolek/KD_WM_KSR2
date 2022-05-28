package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.repository.SummarizerRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Getter
@Service
public class SummarizerService {
    SummarizerRepository summarizerRepository;

    @Autowired
    public SummarizerService(SummarizerRepository summarizerRepository) {
        this.summarizerRepository = summarizerRepository;
    }

    public List<Label> getSummarizers() {
        return summarizerRepository.getSummarizers();
    }
}
