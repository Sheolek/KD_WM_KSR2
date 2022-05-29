package com.example.KSR2.logic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Summary {

    private Quantifier quantifier;
    private List<Label> qualifiers = new ArrayList<>();
    private List<Label> summarizers = new ArrayList<>();
    private Measures measures = new Measures();

    public void calculateMeasures(double[] weights) {
        measures.calculateMeasures(this, weights);
    }

    public boolean addSummarizer(Label summarizer) {
        return summarizers.add(summarizer);
    }

    public boolean addQualifier(Label qualifier) {
        return qualifiers.add(qualifier);
    }

    public boolean removeSummarizer(Label summarizer) {
        return summarizers.remove(summarizer);
    }

    public boolean removeQualifier(Label qualifier) {
        return qualifiers.remove(qualifier);
    }

    public void setQuantifier(Quantifier quantifier) {
        this.quantifier = quantifier;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(quantifier.getLabel().getName());
        if (qualifiers.size() > 0) {
            sb.append(" które są ");
            for (int i = 0; i < qualifiers.size(); i++) {
                sb.append(qualifiers.get(i).getName());
                if (i != qualifiers.size() -1) {
                    sb.append(" i ");
                }
            }
        }
        sb.append(" jest ");
        for (int i = 0; i < summarizers.size(); i++) {
            sb.append(summarizers.get(i).getName());
            if (i != summarizers.size() -1) {
                sb.append(" i ");
            }
        }
        return sb.toString();
    }
}
