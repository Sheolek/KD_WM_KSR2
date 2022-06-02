package com.example.KSR2.logic.model.membershipFunction;

public interface MembershipFunction {
    double getMembership(double value);

    double getCardinalNumber();

    double getSupport();

    default double getNegativeMembership(double value) {
        return 1 - getMembership(value);
    }
}
