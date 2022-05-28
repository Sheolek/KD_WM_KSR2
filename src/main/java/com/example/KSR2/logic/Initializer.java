package com.example.KSR2.logic;

import com.example.KSR2.logic.model.FuzzySet;
import com.example.KSR2.logic.model.Label;
import com.example.KSR2.logic.model.LinguisticVariable;
import com.example.KSR2.logic.model.Quantifier;
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
        initializeFuzzySets();
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

    private void initializeFuzzySets() {
        //cena
        fuzzySets.add(new FuzzySet(variables.get(0).getUniverse(), new Trapezoidal(50000, 50000, 244200, 437400)));
        fuzzySets.add(new FuzzySet(variables.get(0).getUniverse(), new Trapezoidal(244200, 437400, 534000, 727200)));
        fuzzySets.add(new FuzzySet(variables.get(0).getUniverse(), new Trapezoidal(534000, 727200, 823800, 1017000)));
        fuzzySets.add(new FuzzySet(variables.get(0).getUniverse(), new Trapezoidal(823800, 1017000, 1113600, 1306800)));
        fuzzySets.add(new FuzzySet(variables.get(0).getUniverse(), new Trapezoidal(1113600, 1306800, 2500000, 2500000)));
        //powirzchnia dzialki
        fuzzySets.add(new FuzzySet(variables.get(1).getUniverse(), new Trapezoidal(50, 50, 5050, 10050)));
        fuzzySets.add(new FuzzySet(variables.get(1).getUniverse(), new Trapezoidal(5050, 10050, 12550, 17550)));
        fuzzySets.add(new FuzzySet(variables.get(1).getUniverse(), new Trapezoidal(12550, 17550, 20050, 25050)));
        fuzzySets.add(new FuzzySet(variables.get(1).getUniverse(), new Trapezoidal(20050, 25050, 27550, 32550)));
        fuzzySets.add(new FuzzySet(variables.get(1).getUniverse(), new Trapezoidal(527550, 32550, 35050, 40050)));
        fuzzySets.add(new FuzzySet(variables.get(1).getUniverse(), new Trapezoidal(35050, 40050, 1100000, 1100000)));
        //powierzchnia mieszkania
        fuzzySets.add(new FuzzySet(variables.get(2).getUniverse(), new Trapezoidal(0, 0, 60, 180)));
        fuzzySets.add(new FuzzySet(variables.get(2).getUniverse(), new Triangular(120, 240, 360)));
        fuzzySets.add(new FuzzySet(variables.get(2).getUniverse(), new Trapezoidal(300, 420, 1000, 1000)));
        //rok wybudowania
        fuzzySets.add(new FuzzySet(variables.get(3).getUniverse(), new Trapezoidal(1850, 1850, 1860, 1900)));
        fuzzySets.add(new FuzzySet(variables.get(3).getUniverse(), new Triangular(1860, 1900, 1940)));
        fuzzySets.add(new FuzzySet(variables.get(3).getUniverse(), new Triangular(1900, 1940, 1980)));
        fuzzySets.add(new FuzzySet(variables.get(3).getUniverse(), new Triangular(1940, 1980, 2020)));
        fuzzySets.add(new FuzzySet(variables.get(3).getUniverse(), new Trapezoidal(1980, 2020, 2022, 2022)));
        //odlegosc od centrum
        fuzzySets.add(new FuzzySet(variables.get(4).getUniverse(), new Trapezoidal(500, 500, 5500, 15500)));
        fuzzySets.add(new FuzzySet(variables.get(4).getUniverse(), new Triangular(10500, 20500, 30500)));
        fuzzySets.add(new FuzzySet(variables.get(4).getUniverse(), new Triangular(20500, 30500, 40500)));
        fuzzySets.add(new FuzzySet(variables.get(4).getUniverse(), new Trapezoidal(35500, 45500, 70000, 70000)));
        //odleglosc od stacji
        fuzzySets.add(new FuzzySet(variables.get(5).getUniverse(), new Trapezoidal(30, 30, 4030,  8030)));
        fuzzySets.add(new FuzzySet(variables.get(5).getUniverse(), new Trapezoidal(4030,  8030, 10030, 14030)));
        fuzzySets.add(new FuzzySet(variables.get(5).getUniverse(), new Trapezoidal(10030, 14030, 16030, 20030)));
        fuzzySets.add(new FuzzySet(variables.get(5).getUniverse(), new Trapezoidal(16030, 20030,  35000,  35000)));
        //czas od sprzedazy
        fuzzySets.add(new FuzzySet(variables.get(6).getUniverse(), new Trapezoidal(30, 30, 780, 4830)));
        fuzzySets.add(new FuzzySet(variables.get(6).getUniverse(), new Gauss(4830,4800)));
        fuzzySets.add(new FuzzySet(variables.get(6).getUniverse(), new Gauss(9180,4800)));
        fuzzySets.add(new FuzzySet(variables.get(6).getUniverse(), new Trapezoidal(7830, 12630, 15000, 15000)));
        //wysokosc geo
        fuzzySets.add(new FuzzySet(variables.get(7).getUniverse(), new Trapezoidal(-32.5, -32.5, -32.4, -32.2)));
        fuzzySets.add(new FuzzySet(variables.get(7).getUniverse(), new Triangular(-32.3, -32.1, -31.9)));
        fuzzySets.add(new FuzzySet(variables.get(7).getUniverse(), new Trapezoidal(-32, -31.8, -31.5, -31.5)));
        //szerokosc geo
        fuzzySets.add(new FuzzySet(variables.get(8).getUniverse(), new Trapezoidal(115.6, 115.6, 115.7, 115.9)));
        fuzzySets.add(new FuzzySet(variables.get(8).getUniverse(), new Triangular(115.8, 116, 116.2)));
        fuzzySets.add(new FuzzySet(variables.get(8).getUniverse(), new Trapezoidal(116.1, 116.3, 116.3, 116.4)));
        //odleglosc szkola
        fuzzySets.add(new FuzzySet(variables.get(9).getUniverse(), new Trapezoidal(0.05, 0.05, 2.05,  6.05)));
        fuzzySets.add(new FuzzySet(variables.get(9).getUniverse(), new Trapezoidal(2.05,  6.05, 8.05, 12.05)));
        fuzzySets.add(new FuzzySet(variables.get(9).getUniverse(), new Trapezoidal(8.05, 12.05, 14.05, 18.05)));
        fuzzySets.add(new FuzzySet(variables.get(9).getUniverse(), new Trapezoidal(14.05, 18.05, 25, 25)));
        //ranga
        fuzzySets.add(new FuzzySet(variables.get(10).getUniverse(), new Trapezoidal(1, 1, 11, 31)));
        fuzzySets.add(new FuzzySet(variables.get(10).getUniverse(), new Trapezoidal(11, 31, 41, 61)));
        fuzzySets.add(new FuzzySet(variables.get(10).getUniverse(), new Trapezoidal(41, 61, 71, 91)));
        fuzzySets.add(new FuzzySet(variables.get(10).getUniverse(), new Trapezoidal(71, 91, 101, 121)));
        fuzzySets.add(new FuzzySet(variables.get(10).getUniverse(), new Trapezoidal(101, 121, 150, 150)));
    }

    private void initializeSummarizers() {
        for (int i = 0; i < fuzzySets.size(); i++) {
            summarizers.add(new Label(labelsName[i], fuzzySets.get(i)));
        }
    }

    private void initializeQuantifiers() {

    }
}
