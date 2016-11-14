package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum JunctionType {

    INTERSECTION("Intersection",1.0),
    T_JUNCTION("T-Junction",0.67),
    NON_INTERSECTION("Not an intersection",0.33);
    private String junctionType;
    private double valueWt;
    JunctionType(String junctionType, double valueWt){
        this.junctionType = junctionType;
        this.valueWt =valueWt;
    }

    @Override
    public String toString() {
        return junctionType;
    }

    public static double getValueWt(String junctionType) {
        if(junctionType.equals(JunctionType.INTERSECTION.toString())){
            return JunctionType.INTERSECTION.valueWt;
        } else if(junctionType.equals(JunctionType.T_JUNCTION.toString())){
            return JunctionType.T_JUNCTION.valueWt;
        } else if(junctionType.equals(INTERSECTION.NON_INTERSECTION.toString())){
            return INTERSECTION.NON_INTERSECTION.valueWt;
        } else{
            return 0;
        }
    }
}
