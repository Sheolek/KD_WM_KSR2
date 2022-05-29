package com.example.KSR2.logic.model.membershipFunction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Trapezoidal implements MembershipFunction {
    private double leftDown;
    private double leftUp;
    private double rightUp;
    private double rightDown;


    @Override
    public double getMembership(double value) {
        if (value < leftDown) {
            return 0;
        } else if (value < leftUp) {
            return (value - leftDown) / (leftUp - leftDown);
        } else if (value < rightUp) {
            return 1;
        } else if (value < rightDown) {
            return (rightDown - value) / (rightDown - rightUp);
        } else {
            return 0;
        }
    }

    @Override
    public Double[] getSupport() {
        return new Double[]{leftDown, rightDown};
    }

    @Override
    public double getCardinalNumber() {
        return ((rightDown - leftDown) + (rightUp - leftUp)) / 2;
    }
}
