package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum PotholeType {
    TRADITIONAL("Traditional"),
    EARLYSTAGE("In-the-making"),
    TECTONIC("Tectonic"),
    CLIFFHANGER("Cliffhanger"),
    INVERTED("Inverted");
    private String potholeType;
    PotholeType(String potholeType){
        this.potholeType = potholeType;
    }

    @Override
    public String toString() {
        return potholeType;
    }
}
