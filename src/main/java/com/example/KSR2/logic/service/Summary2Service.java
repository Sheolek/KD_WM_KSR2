package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.House;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.Quantifier;
import com.example.KSR2.logic.model.Summary2;
import com.example.KSR2.logic.repository.Summary2Repository;
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
public class Summary2Service {
    Summary2Repository summaryRepository;

    @Autowired
    public Summary2Service(Summary2Repository summaryRepository) {
        this.summaryRepository = summaryRepository;
    }

    public List<Summary2> getSummaries() {
        return summaryRepository.getSummaries();
    }

    public void reset() {
        summaryRepository.reset();
    }

    public void createSummary(Quantifier quantifier, List<Label> qualifiers1, List<Label> qualifiers2, List<Label> summarizers, List<House> obj1, List<House> obj2) {
        Summary2 summary2 = new Summary2() {{
            setQuantifier(quantifier);
            setQualifiers1(qualifiers1);
            setQualifiers2(qualifiers2);
            setSummarizers(summarizers);
            setObjects1(obj1);
            setObjects2(obj2);
        }};
        summary2.calculateT();
        summaryRepository.add(summary2);
    }
}
