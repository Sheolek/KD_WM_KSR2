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
public class Continuous implements ClassicSet {
    List<Double[]> values;

    @Override
    public boolean isIn(double value) {
        for (Double[] range : values) {
            if (value > range[0] & value < range[1]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ClassicSet add(ClassicSet set) {
        Continuous continuous = (Continuous) set;
        List<Double[]> continuousValues = continuous.getValues();
        continuousValues.addAll(values);
        return new Continuous(continuousValues);
    }

    @Override
    public ClassicSet getProduct(ClassicSet set) {
        return null;
    }
}
