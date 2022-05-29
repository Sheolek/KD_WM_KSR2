package com.example.KSR2.logic.repository;

import com.example.KSR2.logic.model.Label;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Repository
public class SummarizerRepository {
    List<Label> summarizers = new ArrayList<>();

    public Label getLabelByName(String name, String variableName) {
        return summarizers.stream().filter(x -> x.getName().equals(name) & x.getVariableName().equals(variableName)).toList().get(0);
    }

    public boolean add(Label summarizer) {
        return summarizers.add(summarizer);
    }

    public boolean remove(Label summarizer) {
        return summarizers.remove(summarizer);
    }

    public Label getSummarizerById(int id) {
        return summarizers.get(id);
    }
}
