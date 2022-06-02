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
    public double getSupport() {
        return function1.getSupport() + function2.getSupport();
    }

    @Override
    public double getCardinalNumber() {
        return function1.getCardinalNumber() * function2.getCardinalNumber();
    }
}
