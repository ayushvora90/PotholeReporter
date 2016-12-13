package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum ImageQualityType {
    HIGH("High",1.0),MEDIUM("Medium",0.67),LOW("Low",0.33);
    private String imageQualityType;
    private double valueWt;

    ImageQualityType(String imageQualityType, double valueWt){
        this.imageQualityType = imageQualityType;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return imageQualityType;
    }

    public static double getValueWt(String imageQualityType){
        if(imageQualityType.equals(ImageQualityType.HIGH.toString())){
            return ImageQualityType.HIGH.valueWt;
        } else if(imageQualityType.equals(ImageQualityType.MEDIUM.toString())){
            return ImageQualityType.MEDIUM.valueWt;
        } else if(imageQualityType.equals(ImageQualityType.LOW.toString())){
            return ImageQualityType.LOW.valueWt;
        } else{
            return 0;
        }
    }
}
