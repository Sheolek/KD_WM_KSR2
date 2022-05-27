package com.example.KSR2.logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quantifier {
    private Label label;
    Boolean isRelative;

    public double getMembership(double value) {
        return label.getMembership(value);
    }
}
