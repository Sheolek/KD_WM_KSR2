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
public class Label {
    @Id
    Long id;
    private String name;
    private FuzzySet set;

    public double getMembership(double value) {
        return set.getMembership(value);
    }
}
