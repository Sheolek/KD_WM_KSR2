package com.example.KSR2.logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Label {
    private String name;
    private FuzzySet set;

    public double getMembership(double value) {
        return set.getMembership(value);
    }
}
