package com.example.KSR2.logic.model.membershipFunction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Gauss implements MembershipFunction {
    private double top;
    private double stdev;

    @Override
    public double getMembership(double value) {
        return 0;
    }

    @Override
    public Double[] getSupport() {
        return new Double[0];
    }
}
