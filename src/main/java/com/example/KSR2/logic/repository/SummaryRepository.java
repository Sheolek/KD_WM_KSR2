package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.Summary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Repository
public class SummaryRepository {
    List<Summary> summaries = new ArrayList<>();

    public boolean add(Summary summary) {
        return summaries.add(summary);
    }

    public boolean remove(Summary summary) {
        return summaries.remove(summary);
    }

    public Summary getSummarizerById(int id) {
        return summaries.get(id);
    }
}
