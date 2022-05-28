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
    private FuzzySet set;

    public Label(String name, LinguisticVariable variable, MembershipFunction function) {
        this.name = name;
        this.set = new FuzzySet(variable.getUniverse(), function);
        variable.addLabel(this);
    }

    public Label addLabel(Label label) {
        return new Label(this.getName() + " i " + label.getName(), set.add(label.getSet()));
    }

    public double getMembership(double value) {
        return set.getMembership(value);
    }
}
