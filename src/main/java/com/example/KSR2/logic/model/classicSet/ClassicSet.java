package com.example.KSR2.logic.model.classicSet;

public interface ClassicSet {
    boolean isIn(double value);

    double getSize();

    ClassicSet add(ClassicSet set);

    ClassicSet getProduct(ClassicSet set);
}
