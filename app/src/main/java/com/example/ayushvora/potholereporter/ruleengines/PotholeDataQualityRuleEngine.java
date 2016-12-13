package com.example.ayushvora.potholereporter.ruleengines;

import com.example.ayushvora.potholereporter.types.ImageQualityType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ayushvora on 11/8/16.
 */

public class PotholeDataQualityRuleEngine {

    public static String getImageQualityString(double megapPixels){
        if(megapPixels>=7.0){
            return ImageQualityType.HIGH.toString();
        } else if(megapPixels>=3.0&&megapPixels<7.0){
            return  ImageQualityType.MEDIUM.toString();
        } else if(megapPixels<3.0){
            return ImageQualityType.LOW.toString();
        } else {
            return "";
        }
    }
    public static double getCompletenessRating(HashMap<String,String> potholeDataMap){
        int count =0;
        for(String key : potholeDataMap.keySet()){
            if(!potholeDataMap.get(key).equals("")){
                count++;
            }
        }
        return count*100.0/potholeDataMap.size();
    }

    public static double getDataQualityScore(HashMap<String,String> potholeDataMap){
        double completnessRating = Double.parseDouble(potholeDataMap.get("completenessRating"));
        double securityRating = Double.parseDouble(potholeDataMap.get("securityRating"));
        String imageQuality = potholeDataMap.get("imageQuality");
        double dataQualityScore = (0.5*completnessRating)+(0.1*securityRating)+(40*ImageQualityType.getValueWt(imageQuality));
        return dataQualityScore;
    }


}
