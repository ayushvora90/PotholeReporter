package com.example.ayushvora.potholereporter.ruleengines;

import com.example.ayushvora.potholereporter.types.AverageSpeed;
import com.example.ayushvora.potholereporter.types.JunctionType;
import com.example.ayushvora.potholereporter.types.PPRRW;
import com.example.ayushvora.potholereporter.types.PriorityType;
import com.example.ayushvora.potholereporter.types.RepairPerformed;
import com.example.ayushvora.potholereporter.types.RoadMaterial;
import com.example.ayushvora.potholereporter.types.SafetyZoneType;
import com.example.ayushvora.potholereporter.types.StreetType;
import com.example.ayushvora.potholereporter.types.TrafficVolume;
import com.example.ayushvora.potholereporter.types.WeatherType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ayushvora on 11/8/16.
 */

public class PotholeDataRuleEngine {
    Map<String,String> potholeDataMap;
    Map<String,Double> fieldWtMap;
    Set<String> usedFields;
    double wtSize;
    public PotholeDataRuleEngine(Map<String,String> potholeDataMap){
        this.potholeDataMap = potholeDataMap;
        fieldWtMap = new HashMap<String,Double>();
        fieldWtMap.put("depth",15.0);
        fieldWtMap.put("streetType",13.0);
        fieldWtMap.put("trafficVolume",11.0);
        fieldWtMap.put("avgSpeed",10.0);
        fieldWtMap.put("Area",10.0);
        fieldWtMap.put("safetyZoneType",9.0);
        fieldWtMap.put("junctionType",8.0);
        fieldWtMap.put("pprrw",8.0);
        fieldWtMap.put("roadMaterial",6.0);
        fieldWtMap.put("repair",5.0);
        fieldWtMap.put("weatherType",5.0);
        usedFields = new HashSet<String>();
    }

    public double evaluate(){
        double priority = 0.0;
        double length = Double.parseDouble(potholeDataMap.get("length"));
        double width = Double.parseDouble(potholeDataMap.get("width"));
        double area = length*width;
        double depth = Double.parseDouble(potholeDataMap.get("depth"));

        usedFields.add("streetType");
        System.out.println(potholeDataMap.get("streetType"));
        double valWt1 = StreetType.getValueWt(potholeDataMap.get("streetType"));
        double fieldWt = fieldWtMap.get("streetType");

        priority+=valWt1*fieldWt;
        System.out.println(priority);
        usedFields.add("weatherType");
        priority+=fieldWtMap.get("weatherType")*WeatherType.getValueWt(potholeDataMap.get("weatherType"));
        System.out.println(priority);
        usedFields.add("depth");
        if(potholeDataMap.get("streetType").equals(StreetType.ARTERIAL.toString())){

            fieldWtMap.put("depth",fieldWtMap.get("depth")+(fieldWtMap.size()-usedFields.size())*1);
            adjustWeights(-1);
            if(potholeDataMap.get("weatherType").equals(WeatherType.SNOWY.toString())||potholeDataMap.get("weatherType").equals(WeatherType.RAINY.toString())){
                fieldWtMap.put("depth",fieldWtMap.get("depth")+(fieldWtMap.size()-usedFields.size())*0.5);
                adjustWeights(-0.5);
            }
        } else if(potholeDataMap.get("streetType").equals(StreetType.COMMERCIAL.toString())){
            fieldWtMap.put("depth",fieldWtMap.get("depth")+(fieldWtMap.size()-usedFields.size())*0.5);
            adjustWeights(-0.5);
            if(potholeDataMap.get("weatherType").equals(WeatherType.SNOWY.toString())||potholeDataMap.get("weatherType").equals(WeatherType.RAINY.toString())){
                fieldWtMap.put("depth",fieldWtMap.get("depth")+(fieldWtMap.size()-usedFields.size())*0.5);
                adjustWeights(-0.5);
            }
        } else {
            if(potholeDataMap.get("weatherType").equals(WeatherType.SNOWY.toString())||potholeDataMap.get("weatherType").equals(WeatherType.RAINY.toString())){
                fieldWtMap.put("depth",fieldWtMap.get("depth")+(fieldWtMap.size()-usedFields.size())*0.5);
                adjustWeights(-0.5);
            }
        }

        if(depth>=15.0){
            priority+=fieldWtMap.get("depth")*1.0;
        } else if(depth<15.0&&depth>=1.0){
            priority+=fieldWtMap.get("depth")*(depth/15.0);
        } else{
            priority+=0.0;
        }
        System.out.println(priority);

        usedFields.add("Area");


        if(area>45.0){
            priority+=fieldWtMap.get("Area")*1.0;
        } else if(area<=45.0&&area>20.0){
            priority+=fieldWtMap.get("Area")*0.9;
        } else if(area<=20.0&&area>10.0){
            priority+=fieldWtMap.get("Area")*0.8;
        } else if(area<=10.0&&area>6.0){
            priority+=fieldWtMap.get("Area")*0.5;
        } else if(area<=6.0&&area>4.0){
            priority+=fieldWtMap.get("Area")*0.4;
        } else if(area<=4.0&&area>2.0){
            priority+=fieldWtMap.get("Area")*0.2;
        } else{
            priority+=0.0;
        }
        System.out.println(priority);

        usedFields.add("trafficVolume");
        if(potholeDataMap.get("streetType").equals(StreetType.ARTERIAL.toString())){
            fieldWtMap.put("trafficVolume",fieldWtMap.get("trafficVolume")+(fieldWtMap.size()-usedFields.size())*1);
            adjustWeights(-1);

        } else if(potholeDataMap.get("streetType").equals(StreetType.COMMERCIAL.toString())){
            fieldWtMap.put("trafficVolume",fieldWtMap.get("trafficVolume")+(fieldWtMap.size()-usedFields.size())*0.5);
            adjustWeights(-0.5);
        }
        priority+=fieldWtMap.get("trafficVolume")*TrafficVolume.getValueWt(potholeDataMap.get("trafficVolume"));
        System.out.println(priority);
        usedFields.add("avgSpeed");
        if(potholeDataMap.get("trafficVolume").equals(TrafficVolume.EXTREMELY_HIGH.toString())||potholeDataMap.get("trafficVolume").equals(TrafficVolume.HIGH.toString())){
            fieldWtMap.put("avgSpeed",fieldWtMap.get("avgSpeed")+(fieldWtMap.size()-usedFields.size())*1);
            adjustWeights(-1);

        } else if(potholeDataMap.get("trafficVolume").equals(TrafficVolume.MEDIUM.toString())){
            fieldWtMap.put("avgSpeed",fieldWtMap.get("avgSpeed")+(fieldWtMap.size()-usedFields.size())*0.5);
            adjustWeights(-0.5);
        }
        priority+=fieldWtMap.get("avgSpeed")*AverageSpeed.getValueWt(potholeDataMap.get("avgSpeed"));
        System.out.println(priority);
        usedFields.add("safetyZoneType");
        priority+=fieldWtMap.get("safetyZoneType")*SafetyZoneType.getValueWt(potholeDataMap.get("safetyZoneType"));
        System.out.println(priority);
        usedFields.add("junctionType");
        priority+=fieldWtMap.get("junctionType")*JunctionType.getValueWt(potholeDataMap.get("junctionType"));
        System.out.println(priority);
        usedFields.add("pprrw");
        priority+=fieldWtMap.get("pprrw")*PPRRW.getValueWt(potholeDataMap.get("pprrw"));
        System.out.println(priority);
        usedFields.add("roadMaterial");
        if(potholeDataMap.get("weatherType").equals(WeatherType.SNOWY.toString())||potholeDataMap.get("weatherType").equals(WeatherType.RAINY.toString())){
            fieldWtMap.put("roadMaterial",fieldWtMap.get("roadMaterial")+(fieldWtMap.size()-usedFields.size())*0.5);
            adjustWeights(-0.5);
        }
        priority+=fieldWtMap.get("roadMaterial")* RoadMaterial.getValueWt(potholeDataMap.get("roadMaterial"));
        System.out.println(priority);
        usedFields.add("repair");
        priority+=fieldWtMap.get("repair")* RepairPerformed.getValueWt(potholeDataMap.get("repair"));
        System.out.println(priority);



        return priority;
    }

    public static String getPriorityString(double priority){
        if(priority>85.0){
            return PriorityType.EXTREMELY_HIGH.toString();
        } else if(priority<=85.0&&priority>70.0){
            return PriorityType.HIGH.toString();
        } else if(priority<=70.0&&priority>50.0){
            return PriorityType.MEDIUM.toString();
        } else if(priority<=50.0&&priority>30){
            return PriorityType.LOW.toString();
        } else{
            return PriorityType.EXTREMELY_LOW.toString();
        }

    }

    private void adjustWeights(double wtAdjustment){
        wtSize = 0;
        for (String field:fieldWtMap.keySet()) {
            if(!usedFields.contains(field)){
                fieldWtMap.put(field,(fieldWtMap.get(field) + wtAdjustment));
            }
            wtSize+=fieldWtMap.get(field);
        }
        System.out.println(wtSize);
    }

//    public static void main(String args[]){
//        HashMap<String,String> potholeDataMap = new HashMap<String,String>();
//        potholeDataMap.put("depth","8");
//        potholeDataMap.put("streetType",StreetType.COMMERCIAL.toString());
//        potholeDataMap.put("trafficVolume",TrafficVolume.MEDIUM.toString());
//        potholeDataMap.put("avgSpeed",AverageSpeed.HIGH.toString());
//        potholeDataMap.put("length","4");
//        potholeDataMap.put("width","3");
//        potholeDataMap.put("safetyZoneType",SafetyZoneType.SCHOOL_ZONE.toString());
//        potholeDataMap.put("junctionType",JunctionType.INTERSECTION.toString());
//        potholeDataMap.put("pprrw", PPRRW.SIDE_CENTER.toString());
//        potholeDataMap.put("roadMaterial",RoadMaterial.ASPHALT.toString());
//        potholeDataMap.put("repair", RepairPerformed.PERMANENT_NONE.toString());
//        potholeDataMap.put("weatherType",WeatherType.RAINY.toString());
//        PotholeDataRuleEngine aruleEngine = new PotholeDataRuleEngine(potholeDataMap);
//        System.out.println(getPriorityString(aruleEngine.evaluate()));
//
//    }

}

