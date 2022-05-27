package com.example.KSR2.logic.model;

import com.example.KSR2.logic.model.classicSet.ClassicSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LinguisticVariable {
    String name;
    ClassicSet universe;
    List<Label> labels;

    public void addLabel(Label label) {
        labels.add(label);
    }

    public void removeLabel(Label label) {
        labels.remove(label);
    }

    public List<Label> getLabels(double value) {
        return labels.stream().filter(x -> x.getMembership(value) > 0).toList();
    }
}
