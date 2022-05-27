package com.example.KSR2.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Measures {
    private double T1degreeOfTruth = 0;
    private double T2degreeOfImprecision = 0;
    private double T3degreeOfCovering = 0;
    private double T4degreeOfAppropriateness = 0;
    private double T5lengthOfSummary = 0;
    private double T6degreeOfQuantifierImprecision = 0;
    private double T7degreeOfQuantifierCardinality = 0;
    private double T8degreeOfSummarizerCardinality = 0;
    private double T9degreeOfQualifierImprecision = 0;
    private double T10degreeOfQualifierCardinality = 0;
    private double T11lengthOfQualifier = 0;
    private double goodnessOfSummary = 0;

    public void calculateMeasures(Summary summary) {
        return;
    }
}
