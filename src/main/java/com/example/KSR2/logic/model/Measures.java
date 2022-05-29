package com.example.KSR2.logic.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    public void calculateMeasures(Summary summary, double[] weights, List<House> houses) {
        List<Label> summarizers = summary.getSummarizers();
        List<Label> qualifiers = summary.getQualifiers();
        Quantifier quantifier = summary.getQuantifier();
        int numOfSummarizers = summarizers.size();
        int numOfQualifiers = qualifiers.size();
        int numOfHouses = houses.size();

        // Miary bez przechodzenia po obiektach
        // T5
        {
            double temp = 2 * Math.pow(0.5, numOfSummarizers);
            setT5lengthOfSummary(temp);
        }

        // T6
        {
            double temp = 0;
            Double[] support = quantifier.getLabel().getSet().getFunction().getSupport();
            if (quantifier.isRelative) {
                temp = 1 - (support[1] - support[0]);
            } else {
                temp = 1 - (support[1] - support[0]) / numOfHouses;
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

        // T2
        int[] cntSummarizersMoreThan0 = new int[numOfSummarizers];

        // T3
        int cntTop = 0;
        int cntBottom = 0;

        // T8
        double[] cardinalSummarizers = new double[numOfSummarizers];

        // T9
        int[] cntQualifiersMoreThan0 = new int[numOfQualifiers];

        // T10
        double[] cardinalQualifiers = new double[numOfQualifiers];

        for (House house : houses) {
            List<Double> summarizerMembership = new ArrayList<>();
            List<Double> qualifierMembership = new ArrayList<>();

            for (int i = 0; i < numOfSummarizers; i++) {
                Label summarizer = summarizers.get(i);
                double membership = summarizer.getMembership(house.getValue(summarizer.getVariableName()));
                summarizerMembership.add(membership);
                cardinalSummarizers[i] += membership;
                if (membership > 0) {
                    cntSummarizersMoreThan0[i] += 1;
                }
            }

            for (int i = 0; i < numOfQualifiers; i++) {
                Label qualifier = qualifiers.get(i);
                double membership = qualifier.getMembership(house.getValue(qualifier.getVariableName()));
                qualifierMembership.add(membership);
                cardinalQualifiers[i] += membership;
                if (membership > 0) {
                    cntQualifiersMoreThan0[i] += 1;
                }
            }

            Double temp = summarizerMembership.stream().sorted().toList().get(0);
            Double temp2 = 1d;

            if (numOfQualifiers > 0) {
                temp2 = qualifierMembership.stream().sorted().toList().get(0);
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

        // T2
        {
            double t2result = 1d;
            for (int i = 0; i < numOfSummarizers; i++) {
                t2result *= (cntSummarizersMoreThan0[i] * 1.0) / numOfHouses;
            }
            t2result = Math.pow(t2result, (1.0 / numOfSummarizers));
            setT2degreeOfImprecision(1 - t2result);
        }

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

        // T8
        {
            double t8result = 1d;
            for (int i = 0; i < numOfSummarizers; i++) {
                t8result *= cardinalSummarizers[i] / cntSummarizersMoreThan0[i];
            }
            t8result = Math.pow(t8result, 1.0/numOfSummarizers);
            t8result = 1 - t8result;
            setT8degreeOfSummarizerCardinality(t8result);
        }

        // T9
        {
            if (numOfQualifiers > 0) {
                double t9result = 1d;
                for (int i = 0; i < numOfQualifiers; i++) {
                    t9result *= (cntQualifiersMoreThan0[i] * 1.0) / numOfHouses;
                }
                t9result = Math.pow(t9result, (1.0 / numOfQualifiers));
                setT9degreeOfQualifierImprecision(1 - t9result);
            } else {
                setT9degreeOfQualifierImprecision(0);
            }
        }

        // T10
        {
            if (numOfQualifiers > 0) {
                double t10result = 1d;
                for (int i = 0; i < numOfQualifiers; i++) {
                    t10result *= cardinalQualifiers[i] / cntQualifiersMoreThan0[i];
                }
                t10result = Math.pow(t10result, 1.0 / numOfQualifiers);
                t10result = 1 - t10result;
                setT10degreeOfQualifierCardinality(t10result);
            } else {
                setT10degreeOfQualifierCardinality(0);
            }
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
