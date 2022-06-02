package com.example.KSR2.logic;

import com.example.KSR2.logic.model.Summary;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SummaryTableRecord {
    private String summary;
    private double T;
    private double T1;
    private double T2;
    private double T3;
    private double T4;
    private double T5;
    private double T6;
    private double T7;
    private double T8;
    private double T9;
    private double T10;
    private double T11;

    public SummaryTableRecord(Summary summary) {

    }
}
