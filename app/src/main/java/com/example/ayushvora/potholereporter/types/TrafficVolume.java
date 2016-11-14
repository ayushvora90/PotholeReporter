package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum TrafficVolume {
    EXTREMELY_HIGH("Extremely High",1.0),
    HIGH("High",0.80),
    MEDIUM("Medium",0.60),
    LOW("Low",0.40),
    EXTREMELY_LOW("Extremely Low",0.20);
    private final String volume;
    private final double valueWt;
    TrafficVolume(String volume, double valueWt){
        this.volume = volume;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return volume;
    }

    public static double getValueWt(String trafficVolume) {
        if(trafficVolume.equals(TrafficVolume.EXTREMELY_HIGH.toString())){
            return TrafficVolume.EXTREMELY_HIGH.valueWt;
        } else if(trafficVolume.equals(TrafficVolume.HIGH.toString())){
            return TrafficVolume.HIGH.valueWt;
        } else if(trafficVolume.equals(TrafficVolume.MEDIUM.toString())){
            return TrafficVolume.MEDIUM.valueWt;
        } else if(trafficVolume.equals(TrafficVolume.LOW.toString())){
            return TrafficVolume.LOW.valueWt;
        } else if(trafficVolume.equals(TrafficVolume.EXTREMELY_LOW.toString())){
            return TrafficVolume.EXTREMELY_LOW.valueWt;
        } else {
            return 0;
        }

    }
}

