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
        double val = Math.pow(1/(stdev * Math.sqrt(2 * Math.PI)),(-0.5 * ((value - top) * (value - top))/(stdev*stdev)));
        if (stdev > 1) {
            val = 1/val;
        }
        return val;
    }

    @Override
    public double getSupport() {
        return stdev*2*2;
    }

    @Override
    public double getCardinalNumber() {
        return 1;
    }
}
