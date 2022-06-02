package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.House;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.Quantifier;
import com.example.KSR2.logic.model.Summary;
import com.example.KSR2.logic.repository.SummaryRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Service
public class SummaryService {
    SummaryRepository summaryRepository;

    @Autowired
    public SummaryService(SummaryRepository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    public List<Summary> getSummaries() {
        return summaryRepository.getSummaries();
    }

    public void reset() {
        summaryRepository.reset();
    }

    public void createSummary(Quantifier quantifier, List<Label> qualifiers, List<Label> summarizers, double[] weights, List<House> houses) {
        Summary summary = new Summary();
        summary.setQuantifier(quantifier);
        for (Label qualifier:qualifiers) {
            summary.addQualifier(qualifier);
        }
        for (Label summarizer:summarizers) {
            summary.addSummarizer(summarizer);
        }
        summary.setObjects(houses);
        summary.calculateMeasures(weights);
        summaryRepository.add(summary);
    }
}
