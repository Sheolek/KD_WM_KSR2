package com.example.KSR2.logic.model;

import com.example.KSR2.logic.model.classicSet.Continuous;
import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;

@Data
@Entity(name = "houses")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SUBURB")
    private String suburb;

    @Column(name = "PRICE")
    private int price;

    @Column(name = "LAND_AREA")
    private int landArea;

    @Column(name = "FLOOR_AREA")
    private int floorArea;

    @Column(name = "BUILD_YEAR")
    private int buildYear;

    @Column(name = "CBD_DIST")
    private int cbdDist;

    @Column(name = "NEAREST_STN_DIST")
    private int nearestStationDist;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "NEAREST_SCH_DIST")
    private double nearestSchoolDist;

    @Column(name = "NEAREST_SCH_RANK")
    private double nearestSchoolRank;

    @Column(name = "LAST_SOLD_TIME")
    private int lastSoldTime;

    public double getValue(String columnName) {
        return switch (columnName) {
            case "Cena" -> price;
            case "Powierzchnia dzialki" -> landArea;
            case "Powierzchnia mieszkania" -> floorArea;
            case "Rok wybudowania" -> buildYear;
            case "Odległosc od centrum" -> cbdDist;
            case "Odleglosc od najblizszej stacji" -> nearestStationDist;
            case "Czas od ostatniej sprzedazy" -> lastSoldTime;
            case "Wysokosc geograficzna" -> latitude;
            case "Szerokosc geograficzna" -> longitude;
            case "Odleglosc od szkoly" -> nearestSchoolDist;
            case "Ranga szkoly" -> nearestSchoolRank;
            default -> 0;
        };
    }
}
