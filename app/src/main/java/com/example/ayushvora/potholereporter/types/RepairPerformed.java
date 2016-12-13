package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 11/12/16.
 */

public enum RepairPerformed {
    TEMPORARY("Temporary",0.0), PERMANENT_NONE("Permanent/None",1.0);
    private final String repair;
    private final double valueWt;
    RepairPerformed(String volume, double valueWt){
        this.repair = volume;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return repair;
    }

    public static double getValueWt(String repairPerformed) {
        if(repairPerformed.equals(RepairPerformed.PERMANENT_NONE.toString())){
            return RepairPerformed.PERMANENT_NONE.valueWt;
        }  else {
            return RepairPerformed.TEMPORARY.valueWt;
        }

    }
}
