package com.example.KSR2.logic.model;

import com.example.KSR2.logic.model.classicSet.ClassicSet;
import com.example.KSR2.logic.model.classicSet.Continuous;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LinguisticVariable {
    String name;
    ClassicSet universe;
    List<Label> labels;

    public LinguisticVariable(String name, ClassicSet set) {
        this.name = name;
        this.universe = set;
        this.labels = new ArrayList<>();
    }

    public Label getLabelByName(String name) {
        return labels.stream().filter(x -> x.getName().equals(name)).toList().get(0);
    }

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
