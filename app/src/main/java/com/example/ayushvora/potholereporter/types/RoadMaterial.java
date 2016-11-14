package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum RoadMaterial {
    CONCRETE("Concrete"),ASPHALT("Asphalt");
    private String roadMaterial;
    RoadMaterial(String roadMaterial){
        this.roadMaterial = roadMaterial;
    }

    @Override
    public String toString() {
        return roadMaterial;
    }
}
