package com.example.KSR2.logic.model.membershipFunction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Triangular implements MembershipFunction {
    private double left;
    private double top;
    private double right;


    @Override
    public double getMembership(double value) {
        if (value < left) {
            return 0;
        } else if (value < top) {
            return (value - left) / (top - left);
        } else if (value == top) {
            return 1;
        } else if (value < right) {
            return (right - value) / (right - top);
        } else {
            return 0;
        }
    }

    @Override
    public Double[] getSupport() {
        return new Double[]{left, right};
    }

    @Override
    public double getCardinalNumber() {
        return (right - left) / 2;
    }
}
