package com.example.KSR2.logic.model;

import com.example.KSR2.logic.model.membershipFunction.MembershipFunction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Label {
    private String name;
    private String variableName;
    private FuzzySet set;

    public Label(String name, LinguisticVariable variable, MembershipFunction function) {
        this.name = name;
        this.variableName = variable.getName();
        this.set = new FuzzySet(variable.getUniverse(), function);
        variable.addLabel(this);
    }

    public double getMembership(double value) {
        return set.getMembership(value);
    }
}
