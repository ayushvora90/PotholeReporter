package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum RoadMaterial {
    CONCRETE("Concrete",0.5),ASPHALT("Asphalt",1.0);
    private final String roadMaterial;
    private final double valueWt;
    RoadMaterial(String roadMaterial, double valueWt){
        this.roadMaterial = roadMaterial;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return roadMaterial;
    }

    public static double getValueWt(String roadMaterial) {
        if(roadMaterial.equals(RoadMaterial.ASPHALT.toString())){
            return RoadMaterial.ASPHALT.valueWt;
        }  else {
            return RoadMaterial.CONCRETE.valueWt;
        }
    }
}
