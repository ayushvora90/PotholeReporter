package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum SafetyZoneType {
    SCHOOL_ZONE("School Zone",1.00),ROAD_CONSTRUCTION_ZONE("Road/Construction Zone",0.50),NONE("None",0.00);
    private String safetyZoneType;
    private double valueWt;
    SafetyZoneType(String safetyZoneType, double valueWt){
        this.safetyZoneType = safetyZoneType;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return safetyZoneType;
    }

    public static double getValueWt(String safetyZoneType){
        if(safetyZoneType.equals(SafetyZoneType.SCHOOL_ZONE.toString())){
            return SafetyZoneType.SCHOOL_ZONE.valueWt;
        } else if(safetyZoneType.equals(SafetyZoneType.ROAD_CONSTRUCTION_ZONE.toString())){
            return SafetyZoneType.ROAD_CONSTRUCTION_ZONE.valueWt;
        } else if(safetyZoneType.equals(SafetyZoneType.NONE.toString())){
            return SafetyZoneType.NONE.valueWt;
        } else {
            return 0;
        }
    }
}
