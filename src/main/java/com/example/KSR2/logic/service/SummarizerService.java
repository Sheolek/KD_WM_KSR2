package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.repository.SummarizerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
public class SummarizerService {
    SummarizerRepository summarizerRepository;

    public List<Label> getSummarizers() {
        return summarizerRepository.getSummarizers();
    }
}
