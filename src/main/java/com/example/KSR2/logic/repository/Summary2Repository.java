package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.Summary2;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Repository
public class Summary2Repository {
    List<Summary2> summaries = new ArrayList<>();

    public boolean add(Summary2 summary) {
        return summaries.add(summary);
    }

    public boolean remove(Summary2 summary) {
        return summaries.remove(summary);
    }

    public Summary2 getSummarizerById(int id) {
        return summaries.get(id);
    }

    public void reset() {
        summaries = new ArrayList<>();
    }
}
