package com.example.KSR2.logic.model.membershipFunction;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Singleton implements MembershipFunction {
    private List<Double> ones;

    void add(Double value) {
        ones.add(value);
    }

    void remove(Double value) {
        ones.remove(value);
    }

    @Override
    public double getMembership(double value) {
        if (ones.contains(value)) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Double[] getSupport() {
        return this.ones.toArray(new Double[ones.size()]);
    }

    @Override
    public double getCardinalNumber() {
        return 0;
    }
}
