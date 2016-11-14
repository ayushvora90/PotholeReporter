package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum PPRRW {
    SIDES("Sides",0.33),SIDE_CENTER("Side-Center",0.67),CENTER("Center",1.00);
    private double valueWt;
    private String pprrw;
    PPRRW(String pprrw,double valueWt){
        this.pprrw = pprrw;
        this.valueWt = valueWt;
    }
    @Override
    public String toString() {
        return pprrw;
    }


    public static double getValueWt(String pprrw) {
        if(pprrw.equals(PPRRW.SIDES.toString())){
            return PPRRW.SIDES.valueWt;
        } else if(pprrw.equals(PPRRW.SIDE_CENTER.toString())){
            return PPRRW.SIDE_CENTER.valueWt;
        } else if(pprrw.equals(PPRRW.CENTER.toString())){
            return PPRRW.CENTER.valueWt;
        } else{
            return 0;
        }
    }
}
