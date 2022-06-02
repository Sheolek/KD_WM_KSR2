package com.example.KSR2.logic.model.classicSet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Discrete implements ClassicSet {
    private List<Double> values;

    @Override
    public boolean isIn(double value) {
        return values.contains(value);
    }

    @Override
    public double getSize() {
        return values.size();
    }

    @Override
    public ClassicSet add(ClassicSet set) {
        Discrete discrete = (Discrete) set;
        List<Double> discreteValues = discrete.getValues();
        discreteValues.addAll(values);
        return new Discrete(discreteValues);
    }

    @Override
    public ClassicSet getProduct(ClassicSet set) {
        Discrete discrete = (Discrete) set;
        List<Double> discreteValues = discrete.getValues();
        List<Double> result = discreteValues.stream().filter(x -> values.contains(x)).toList();
        return new Discrete(result);
    }
}
