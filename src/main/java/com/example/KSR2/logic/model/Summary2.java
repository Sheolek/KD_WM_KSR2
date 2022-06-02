package com.example.KSR2.logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Summary2 {
    private Quantifier quantifier;
    private List<Label> qualifiers1 = new ArrayList<>();
    private List<Label> qualifiers2 = new ArrayList<>();
    private List<Label> summarizers = new ArrayList<>();
    private List<House> objects1;
    private List<House> objects2;
    private double T;

    public void calculateT() {
        this.T = 0;
    }
}
