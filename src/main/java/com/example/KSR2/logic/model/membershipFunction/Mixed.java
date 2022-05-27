package com.example.KSR2.logic.model.membershipFunction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mixed implements MembershipFunction {
    MembershipFunction function1;
    MembershipFunction function2;

    @Override
    public double getMembership(double value) {
        return Math.min(function1.getMembership(value), function2.getMembership(value));
    }

    @Override
    public Double[] getSupport() {
        return new Double[0];
    }
}
