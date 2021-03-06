package com.example.KSR2.logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
        List<House> all = Stream.concat(objects1.stream(),objects2.stream()).toList();
        double obj1Mem = getSigmaCount(objects1,summarizers);
        double obj2Mem = getSigmaCount(objects2,summarizers);
        int m1 = objects1.size();
        int m2 = objects2.size();
        if (quantifier == null) {
            double sum = 0;
            for (House house:all) {
                double mem1 = 0;
                double mem2 = 0;
                List<Double> summarizerMembership = new ArrayList<>();
                for (Label label : summarizers) {
                    double membership = label.getMembership(house.getValue(label.getVariableName()));
                    summarizerMembership.add(membership);
                }
                double membership = Collections.min(summarizerMembership);
                if (objects1.contains(house)) {
                    sum += implicationLukasiewicz(0,membership);
                }
                if (objects2.contains(house)) {
                    sum += implicationLukasiewicz(membership,0);
                }
            }
            this.T = 1 - sum/all.size();
        } else {
            if (qualifiers1.size() > 0) {
                double obj1MemWQual = getSigmaCount(objects1, Stream.concat(qualifiers1.stream(),summarizers.stream()).toList());
                double temp = (obj1MemWQual/m1)/(obj1MemWQual/m1 + obj2Mem/m2);
                this.T = quantifier.getMembership(temp);
            } else if (qualifiers2.size() > 0) {
                double obj2MemWQual = getSigmaCount(objects2, Stream.concat(qualifiers2.stream(),summarizers.stream()).toList());
                double temp = (obj1Mem/m1)/(obj1Mem/m1 + obj2MemWQual/m2);
                this.T = quantifier.getMembership(temp);
            } else {
                double temp = (obj1Mem/m1)/(obj1Mem/m1 + obj2Mem/m2);
                this.T = quantifier.getMembership(temp);
            }
        }
    }

    private double getSigmaCount(List<House> objects, List<Label> labels) {
        double sum = 0;
        for (House house: objects) {
            List<Double> summarizerMembership = new ArrayList<>();
            for (Label label : labels) {
                double membership = label.getMembership(house.getValue(label.getVariableName()));
                summarizerMembership.add(membership);
            }
            double membership = Collections.min(summarizerMembership);
            sum += membership;
        }
        return sum;
    }

    private double implicationLukasiewicz(double member1, double member2) {
        return Math.min(1, 1-member1 + member2);
    }
}
