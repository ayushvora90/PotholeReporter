package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 11/12/16.
 */

public enum AverageSpeed {
    EXTREMELY_HIGH("Extremely High",1.0),
    HIGH("High",0.80),
    MEDIUM("Medium",0.60),
    LOW("Low",0.40),
    EXTREMELY_LOW("Extremely Low",0.20);
    private final String volume;
    private final double valueWt;
    AverageSpeed(String volume, double valueWt){
        this.volume = volume;
        this.valueWt = valueWt;
    }
    public String toString() {
        return volume;
    }

    public static double getValueWt(String avgSpeed) {
        if(avgSpeed.equals(AverageSpeed.EXTREMELY_HIGH.toString())){
            return AverageSpeed.EXTREMELY_HIGH.valueWt;
        } else if(avgSpeed.equals(AverageSpeed.HIGH.toString())){
            return AverageSpeed.HIGH.valueWt;
        } else if(avgSpeed.equals(AverageSpeed.MEDIUM.toString())){
            return AverageSpeed.MEDIUM.valueWt;
        } else if(avgSpeed.equals(AverageSpeed.LOW.toString())){
            return AverageSpeed.LOW.valueWt;
        } else if(avgSpeed.equals(AverageSpeed.EXTREMELY_LOW.toString())){
            return AverageSpeed.EXTREMELY_LOW.valueWt;
        } else {
            return 0;
        }

    }
}
