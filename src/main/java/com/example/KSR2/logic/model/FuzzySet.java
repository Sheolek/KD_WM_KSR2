package com.example.KSR2.logic.model;

import com.example.KSR2.logic.model.classicSet.ClassicSet;
import com.example.KSR2.logic.model.membershipFunction.MembershipFunction;
import com.example.KSR2.logic.model.membershipFunction.Mixed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FuzzySet {
    private ClassicSet universe;
    private MembershipFunction function;

    public double getMembership(double value) {
        return function.getMembership(value);
    }

    public FuzzySet add(FuzzySet set) {
        return new FuzzySet(universe.add(set.universe), new Mixed(function, set.function));
    }
}
