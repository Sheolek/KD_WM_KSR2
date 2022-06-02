package com.example.KSR2.logic.model.membershipFunction;

public class ONE implements MembershipFunction{
    @Override
    public double getMembership(double value) {
        return 1;
    }

    @Override
    public double getSupport() {
        return 1;
    }

    @Override
    public double getCardinalNumber(){
        return 1;
    }
}
