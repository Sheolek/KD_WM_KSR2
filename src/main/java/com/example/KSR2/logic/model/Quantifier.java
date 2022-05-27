package com.example.KSR2.logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Quantifier {
    @Id
    private Long Id;
    private Label label;
    Boolean isRelative;

    public double getMembership(double value) {
        return label.getMembership(value);
    }
}
