package com.example.KSR2.logic.model;

import lombok.Data;

import javax.persistence.*;

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
}
