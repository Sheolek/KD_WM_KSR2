package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.Summary;
import com.example.KSR2.logic.repository.SummaryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Service
public class SummaryService {
    SummaryRepository summaryRepository;

    public List<Summary> getSummaries() {
        return summaryRepository.getSummaries();
    }
}
