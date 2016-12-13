package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum StreetType {
    ARTERIAL("Arterial Road",1.00),COMMERCIAL("Commercial Street",0.75),RESIDENTIAL("Residential Street",0.5),ALLEYWAY("Alleway",0.25);
    private String streetType;
    private double valueWt;
    StreetType(String streetType, double valueWt){
        this.streetType = streetType;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return streetType;
    }

    public static double getValueWt(String streetType){
        if(streetType.equals(StreetType.ARTERIAL.toString())){
            return StreetType.ARTERIAL.valueWt;
        } else if(streetType.equals(StreetType.COMMERCIAL.toString())){
            return StreetType.COMMERCIAL.valueWt;
        } else if(streetType.equals(StreetType.RESIDENTIAL.toString())){
            return StreetType.RESIDENTIAL.valueWt;
        } else if(streetType.equals(StreetType.ALLEYWAY.toString())){
            return StreetType.ALLEYWAY.valueWt;
        } else {
            return 0;
        }
    }
}
