package com.example.KSR2.logic.model.classicSet;

public interface ClassicSet {
    boolean isIn(double value);

    ClassicSet add(ClassicSet set);

    ClassicSet getProduct(ClassicSet set);
}
