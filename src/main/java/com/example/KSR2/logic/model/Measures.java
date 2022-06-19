package com.example.KSR2.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Component
public class Measures {

    private double[] weights;
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

    public void calculateMeasures(Summary summary, double[] weights) {
        List<House> houses = summary.getObjects();
        List<Label> summarizers = summary.getSummarizers();
        List<Label> qualifiers = summary.getQualifiers();
        Quantifier quantifier = summary.getQuantifier();
        int numOfSummarizers = summarizers.size();
        int numOfQualifiers = qualifiers.size();
        int numOfHouses = houses.size();

        // Miary bez przechodzenia po obiektach
        // T2
        {
            double temp = 1;
            //support/przestrzen
            for (Label summarizer : summarizers) {
                temp *= summarizer.getSet().getFunction().getSupport() / summarizer.getSet().getUniverse().getSize();
            }
            temp = Math.pow(temp, 1.0/numOfSummarizers);
            setT2degreeOfImprecision(1 - temp);
        }
        // T5
        {
            double temp = 2 * Math.pow(0.5, numOfSummarizers);
            setT5lengthOfSummary(temp);
        }

        // T6
        {
            double temp = 0;
            double support = quantifier.getLabel().getSet().getFunction().getSupport();
            if (quantifier.isRelative) {
                temp = 1 - support;
            } else {
                temp = 1 - support / numOfHouses;
            }
            setT6degreeOfQuantifierImprecision(temp);
        }

        // T7
        {
            double temp = 0;
            double cardinal = quantifier.getLabel().getSet().getFunction().getCardinalNumber();
            if (quantifier.isRelative) {
                temp = 1 - cardinal;
            } else {
                temp = 1 - cardinal / numOfHouses;
            }
            setT7degreeOfQuantifierCardinality(temp);
        }

        // T8
        {
            double temp = 1;
            //cardinal/przestrzen
            for (Label summarizer : summarizers) {
                temp *= summarizer.getSet().getFunction().getCardinalNumber() / summarizer.getSet().getUniverse().getSize();
            }
            temp = Math.pow(temp, 1.0/numOfSummarizers);
            setT8degreeOfSummarizerCardinality(1 - temp);
        }

        // T9
        {
            if(numOfQualifiers > 0){
                double temp = 1;
                //support/przestrzen
                for (Label qualifier : qualifiers) {
                    temp *= qualifier.getSet().getFunction().getSupport() / qualifier.getSet().getUniverse().getSize();
                }
                temp = Math.pow(temp, 1.0 / numOfQualifiers);
                setT9degreeOfQualifierImprecision(1 - temp);
            } else {
                setT9degreeOfQualifierImprecision(0);
            }
        }

        // T10
        {
            if(numOfQualifiers > 0) {
                double temp = 1;
                //cardinal/przestrzen
                for (Label qualifier : qualifiers) {
                    temp *= qualifier.getSet().getFunction().getCardinalNumber() / qualifier.getSet().getUniverse().getSize();
                }
                temp = Math.pow(temp, 1.0 / numOfQualifiers);
                setT10degreeOfQualifierCardinality(1 - temp);
            } else {
                setT10degreeOfQualifierCardinality(0);
            }
        }

        // T11
        {
            if (numOfQualifiers > 0) {
                double temp = 2 * Math.pow(0.5, numOfQualifiers);
                setT11lengthOfQualifier(temp);
            } else {
                setT11lengthOfQualifier(0);
            }
        }

        //----------------Miary wymagające przejścia--------------------
        // T1
        double rtop = 0;
        double rbottom = 0;

        // T3
        int[] cntSummarizersMoreThan0 = new int[numOfSummarizers];
        int cntTop = 0;
        int cntBottom = 0;

        for (House house : houses) {
            List<Double> summarizerMembership = new ArrayList<>();
            List<Double> qualifierMembership = new ArrayList<>();

            for (int i = 0; i < numOfSummarizers; i++) {
                Label summarizer = summarizers.get(i);
                double membership = summarizer.getMembership(house.getValue(summarizer.getVariableName()));
                summarizerMembership.add(membership);
                if (membership > 0) {
                    cntSummarizersMoreThan0[i] += 1;
                }
            }

            for (Label qualifier : qualifiers) {
                double membership = qualifier.getMembership(house.getValue(qualifier.getVariableName()));
                qualifierMembership.add(membership);
            }

            Double temp = Collections.min(summarizerMembership);
            Double temp2 = 1d;

            if (numOfQualifiers > 0) {
                temp2 = Collections.min(qualifierMembership);
            }

            double membership = Math.min(temp, temp2);

            // T1
            rtop += membership;
            rbottom += temp2;

            // T3
            if (membership > 0) {
                cntTop += 1;
            }

            if (temp2 > 0) {
                cntBottom += 1;
            }
        }

        setT1degreeOfTruth(quantifier.getMembership(rtop / rbottom));

        // T3
        setT3degreeOfCovering(cntTop * 1.0 / cntBottom);

        // T4
        {
            double t4result = 1d;
            for (int i = 0; i < numOfSummarizers; i++) {
                t4result *= (cntSummarizersMoreThan0[i] * 1.0) / numOfHouses;
            }
            t4result = t4result - getT3degreeOfCovering();
            setT4degreeOfAppropriateness(Math.abs(t4result));
        }

        // goodnesOfSummary
        {
            double[] measures = new double[]{
                    T1degreeOfTruth,
                    T2degreeOfImprecision,
                    T3degreeOfCovering,
                    T4degreeOfAppropriateness,
                    T5lengthOfSummary,
                    T6degreeOfQuantifierImprecision,
                    T7degreeOfQuantifierCardinality,
                    T8degreeOfSummarizerCardinality,
                    T9degreeOfQualifierImprecision,
                    T10degreeOfQualifierCardinality,
                    T11lengthOfQualifier
            };
            double sum = 0;
            for (int i = 0; i < 11; i++) {
                sum += weights[i] * measures[i];
            }
            setGoodnessOfSummary(sum);
        }
    }
}
