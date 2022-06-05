package com.example.KSR2.logic;

import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.Summary2;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summary2TableRecord {
    private String summary2;
    private double T;

    public Summary2TableRecord(Summary2 summary) {
        String obj1;
        String obj2;
        List<Label> qualifiers1 = summary.getQualifiers1();
        List<Label> qualifiers2 = summary.getQualifiers2();
        List<Label> summarizers = summary.getSummarizers();
        if (summary.getObjects1().get(0).getBedrooms() > 3) {
            obj1 = " mających więcej niż 3 sypialnie ";
            obj2 = " mających 3 lub mniej sypialnie ";
        } else {
            obj1 = " mających 3 lub mniej sypialnie ";
            obj2 = " mających więcej niż 3 sypialnie ";
        }
        this.T = summary.getT();
        StringBuilder builder = new StringBuilder();
        if (summary.getQuantifier() == null) {
            builder.append("Więcej domów");
        } else {
            builder.append(summary.getQuantifier().getLabel().getName());
        }
        builder.append(obj1);
        if (summary.getQualifiers1().size() > 0) {
            builder.append("które są/mają ");
            for (int i = 0; i < qualifiers1.size(); i++) {
                builder.append(qualifiers1.get(i).getName()).append(" ").append(qualifiers1.get(i).getVariableName());
                if (i != qualifiers1.size() -1) {
                    builder.append(" i ");
                }
            }
        }
        builder.append(" w porównaniu do tych ");
        builder.append(obj2);
        if (summary.getQualifiers2().size() > 0) {
            builder.append("które są/mają ");
            for (int i = 0; i < qualifiers2.size(); i++) {
                builder.append(qualifiers2.get(i).getName()).append(" ").append(qualifiers2.get(i).getVariableName());
                if (i != qualifiers2.size() -1) {
                    builder.append(" i ");
                }
            }
        }
        builder.append(" jest/ma ");
        for (int i = 0; i < summarizers.size(); i++) {
            builder.append(summarizers.get(i).getName()).append(" ").append(summarizers.get(i).getVariableName());
            if (i != summarizers.size() -1) {
                builder.append(" i ");
            }
        }
        this.T = round(T);
        this.summary2 = builder.toString();
    }

    private double round(double val) {
        return Math.round(val * 100.0) / 100.0;
    }
}
