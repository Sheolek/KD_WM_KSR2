package com.example.KSR2.logic;

import com.example.KSR2.logic.model.FuzzySet;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.LinguisticVariable;
import com.example.KSR2.logic.model.Quantifier;
import com.example.KSR2.logic.model.classicSet.ClassicSet;
import com.example.KSR2.logic.model.classicSet.Continuous;
import com.example.KSR2.logic.model.membershipFunction.Gauss;
import com.example.KSR2.logic.model.membershipFunction.Trapezoidal;
import com.example.KSR2.logic.model.membershipFunction.Triangular;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class Initializer {
    List<LinguisticVariable> quantifiersVariable = new ArrayList<>();
    List<LinguisticVariable> variables = new ArrayList<>();
    List<FuzzySet> fuzzySets = new ArrayList<>();
    List<Label> summarizers = new ArrayList<>();
    List<Quantifier> quantifiers = new ArrayList<>();
    final String[] labelsName = {"bardzo tani", "tani", "w rozsądnej cenie", "drogi", "bardzo drogi",
            "bardzo mała", "mała", "średnia", "duża", "bardzo duża", "ogromna",
            "klaustrofobiczne", "przecietne", "przestrzenne",
            "bardzo stary", "stary", "w średnim wieku", "młody", "nowy",
            "bardzo blisko centrum", "nieopodal centrum", "daleko od centrum", "bardzo daleko od centrum",
            "bardzo blisko", "w pobliżu", "daleko", "bardzo daleko",
            "ledwie co", "ostatnio", "dawno", "bardzo dawno",
            "na południu", "w centrum", "na północy",
            "na wschodzie", "w centrum", "na zachodzie",
            "bardzo blisko", "w pobliżu", "daleko", "bardzo daleko",
            "wybitna szkoła", "dobra szkoła", "średnia szkoła", "słaba szkoła", "bardzo słaba szkoła"};

    public Initializer() {
        initializeVariables();
        initializeSummarizers();
        initializeQuantifiers();
    }

    private void initializeVariables() {
        variables.add(new LinguisticVariable("Cena", new Continuous(Arrays.asList(new Double[][]{{50000d, 2500000d}}))));
        variables.add(new LinguisticVariable("Powierzchnia dzialki", new Continuous(Arrays.asList(new Double[][]{{50d, 1100000d}}))));
        variables.add(new LinguisticVariable("Powierzchnia mieszkania", new Continuous(Arrays.asList(new Double[][]{{1d, 1000d}}))));
        variables.add(new LinguisticVariable("Rok wybudowania", new Continuous(Arrays.asList(new Double[][]{{1850d, 2022d}}))));
        variables.add(new LinguisticVariable("Odległosc od centrum", new Continuous(Arrays.asList(new Double[][]{{500d, 70000d}}))));
        variables.add(new LinguisticVariable("Odleglosc od najblizszej stacji", new Continuous(Arrays.asList(new Double[][]{{30d, 35000d}}))));
        variables.add(new LinguisticVariable("Czas od ostatniej sprzedazy", new Continuous(Arrays.asList(new Double[][]{{30d, 15000d}}))));
        variables.add(new LinguisticVariable("Wysokosc geograficzna", new Continuous(Arrays.asList(new Double[][]{{-32.5, -31.5}}))));
        variables.add(new LinguisticVariable("Szerokosc geograficzna", new Continuous(Arrays.asList(new Double[][]{{115.6, 116.4}}))));
        variables.add(new LinguisticVariable("Odleglosc od szkoly", new Continuous(Arrays.asList(new Double[][]{{0.05, 25d}}))));
        variables.add(new LinguisticVariable("Ranga szkoly", new Continuous(Arrays.asList(new Double[][]{{1d, 150d}}))));
    }

    private void initializeSummarizers() {
        //cena
        summarizers.add(new Label(labelsName[0], variables.get(0), new Trapezoidal(50000, 50000, 244200, 437400)));
        summarizers.add(new Label(labelsName[1], variables.get(0), new Trapezoidal(244200, 437400, 534000, 727200)));
        summarizers.add(new Label(labelsName[2], variables.get(0), new Trapezoidal(534000, 727200, 823800, 1017000)));
        summarizers.add(new Label(labelsName[3], variables.get(0), new Trapezoidal(823800, 1017000, 1113600, 1306800)));
        summarizers.add(new Label(labelsName[4], variables.get(0), new Trapezoidal(1113600, 1306800, 2500000, 2500000)));
        //powirzchnia dzialki
        summarizers.add(new Label(labelsName[5], variables.get(1), new Trapezoidal(50, 50, 5050, 10050)));
        summarizers.add(new Label(labelsName[6], variables.get(1), new Trapezoidal(5050, 10050, 12550, 17550)));
        summarizers.add(new Label(labelsName[7], variables.get(1), new Trapezoidal(12550, 17550, 20050, 25050)));
        summarizers.add(new Label(labelsName[8], variables.get(1), new Trapezoidal(20050, 25050, 27550, 32550)));
        summarizers.add(new Label(labelsName[9], variables.get(1), new Trapezoidal(527550, 32550, 35050, 40050)));
        summarizers.add(new Label(labelsName[10], variables.get(1), new Trapezoidal(35050, 40050, 1100000, 1100000)));
        //powierzchnia mieszkania
        summarizers.add(new Label(labelsName[11], variables.get(2), new Trapezoidal(0, 0, 60, 180)));
        summarizers.add(new Label(labelsName[12], variables.get(2), new Triangular(120, 240, 360)));
        summarizers.add(new Label(labelsName[13], variables.get(2), new Trapezoidal(300, 420, 1000, 1000)));
        //rok wybudowania
        summarizers.add(new Label(labelsName[14], variables.get(3), new Trapezoidal(1850, 1850, 1860, 1900)));
        summarizers.add(new Label(labelsName[15], variables.get(3), new Triangular(1860, 1900, 1940)));
        summarizers.add(new Label(labelsName[16], variables.get(3), new Triangular(1900, 1940, 1980)));
        summarizers.add(new Label(labelsName[17], variables.get(3), new Triangular(1940, 1980, 2020)));
        summarizers.add(new Label(labelsName[18], variables.get(3), new Trapezoidal(1980, 2020, 2022, 2022)));
        //odlegosc od centrum
        summarizers.add(new Label(labelsName[19], variables.get(4), new Trapezoidal(500, 500, 5500, 15500)));
        summarizers.add(new Label(labelsName[20], variables.get(4), new Triangular(10500, 20500, 30500)));
        summarizers.add(new Label(labelsName[21], variables.get(4), new Triangular(20500, 30500, 40500)));
        summarizers.add(new Label(labelsName[22], variables.get(4), new Trapezoidal(35500, 45500, 70000, 70000)));
        //odleglosc od stacji
        summarizers.add(new Label(labelsName[23], variables.get(5), new Trapezoidal(30, 30, 4030,  8030)));
        summarizers.add(new Label(labelsName[24], variables.get(5), new Trapezoidal(4030,  8030, 10030, 14030)));
        summarizers.add(new Label(labelsName[25], variables.get(5), new Trapezoidal(10030, 14030, 16030, 20030)));
        summarizers.add(new Label(labelsName[26], variables.get(5), new Trapezoidal(16030, 20030,  35000,  35000)));
        //czas od sprzedazy
        summarizers.add(new Label(labelsName[27], variables.get(6), new Trapezoidal(30, 30, 780, 4830)));
        summarizers.add(new Label(labelsName[28], variables.get(6), new Gauss(4830,4800)));
        summarizers.add(new Label(labelsName[29], variables.get(6), new Gauss(9180,4800)));
        summarizers.add(new Label(labelsName[30], variables.get(6), new Trapezoidal(7830, 12630, 15000, 15000)));
        //wysokosc geo
        summarizers.add(new Label(labelsName[31], variables.get(7), new Trapezoidal(-32.5, -32.5, -32.4, -32.2)));
        summarizers.add(new Label(labelsName[32], variables.get(7), new Triangular(-32.3, -32.1, -31.9)));
        summarizers.add(new Label(labelsName[33], variables.get(7), new Trapezoidal(-32, -31.8, -31.5, -31.5)));
        //szerokosc geo
        summarizers.add(new Label(labelsName[34], variables.get(8), new Trapezoidal(115.6, 115.6, 115.7, 115.9)));
        summarizers.add(new Label(labelsName[35], variables.get(8), new Triangular(115.8, 116, 116.2)));
        summarizers.add(new Label(labelsName[36], variables.get(8), new Trapezoidal(116.1, 116.3, 116.3, 116.4)));
        //odleglosc szkola
        summarizers.add(new Label(labelsName[37], variables.get(9), new Trapezoidal(0.05, 0.05, 2.05,  6.05)));
        summarizers.add(new Label(labelsName[38], variables.get(9), new Trapezoidal(2.05,  6.05, 8.05, 12.05)));
        summarizers.add(new Label(labelsName[39], variables.get(9), new Trapezoidal(8.05, 12.05, 14.05, 18.05)));
        summarizers.add(new Label(labelsName[40], variables.get(9), new Trapezoidal(14.05, 18.05, 25, 25)));
        //ranga
        summarizers.add(new Label(labelsName[41], variables.get(10), new Trapezoidal(1, 1, 11, 31)));
        summarizers.add(new Label(labelsName[42], variables.get(10), new Trapezoidal(11, 31, 41, 61)));
        summarizers.add(new Label(labelsName[43], variables.get(10), new Trapezoidal(41, 61, 71, 91)));
        summarizers.add(new Label(labelsName[44], variables.get(10), new Trapezoidal(71, 91, 101, 121)));
        summarizers.add(new Label(labelsName[45], variables.get(10), new Trapezoidal(101, 121, 150, 150)));
    }

    private void initializeQuantifiers() {
        quantifiersVariable.add(new LinguisticVariable("Względne", new Continuous(Arrays.asList(new Double[][]{{0d, 1d}}))));
        quantifiersVariable.add(new LinguisticVariable("Bezwględne", new Continuous(Arrays.asList(new Double[][]{{0d, 19760d}}))));
        //wzgledne
        quantifiers.add(new Quantifier(new Label("Mniej niż ćwirć domów", quantifiersVariable.get(0), new Trapezoidal(0,0,0.04,0.25)),Boolean.TRUE));
        quantifiers.add(new Quantifier(new Label("Mniej niż połowa domów", quantifiersVariable.get(0), new Trapezoidal(0.12,0.2,0.32,0.5)),Boolean.TRUE));
        quantifiers.add(new Quantifier(new Label("Około połowy domów", quantifiersVariable.get(0), new Trapezoidal(0.32,0.44,0.56,0.68)),Boolean.TRUE));
        quantifiers.add(new Quantifier(new Label("Większość domów", quantifiersVariable.get(0), new Trapezoidal(0.6,0.68,0.84,0.88)),Boolean.TRUE));
        quantifiers.add(new Quantifier(new Label("Prawie każdy z domów", quantifiersVariable.get(0), new Trapezoidal(0.84,0.92,1,1)),Boolean.TRUE));

        //bezwzgledne
        quantifiers.add(new Quantifier(new Label("Mniej niż 3000 domów", quantifiersVariable.get(1), new Trapezoidal(0,0,3600,6200)),Boolean.FALSE));
        quantifiers.add(new Quantifier(new Label("Około 7500 domów", quantifiersVariable.get(1), new Triangular(4900,7500,10100)),Boolean.FALSE));
        quantifiers.add(new Quantifier(new Label("Blisko 11000 z domów", quantifiersVariable.get(1), new Triangular(8800,11400,14000)),Boolean.FALSE));
        quantifiers.add(new Quantifier(new Label("Ponad 13000 domów", quantifiersVariable.get(1), new Trapezoidal(12700,15300,19208,19208)),Boolean.FALSE));
    }
}
