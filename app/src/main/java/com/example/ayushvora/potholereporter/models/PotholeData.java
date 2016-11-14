package com.example.ayushvora.potholereporter.models;

import com.example.ayushvora.potholereporter.types.ImageQualityType;
import com.example.ayushvora.potholereporter.types.JunctionType;
import com.example.ayushvora.potholereporter.types.PPRRW;
import com.example.ayushvora.potholereporter.types.PotholeType;
import com.example.ayushvora.potholereporter.types.PriorityType;
import com.example.ayushvora.potholereporter.types.RoadMaterial;
import com.example.ayushvora.potholereporter.types.SafetyZoneType;
import com.example.ayushvora.potholereporter.types.SecurityRating;
import com.example.ayushvora.potholereporter.types.StreetType;
import com.example.ayushvora.potholereporter.types.TrafficVolume;
import com.example.ayushvora.potholereporter.types.WeatherType;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by ayushvora on 10/23/16.
 */

public class PotholeData {

    private HashMap<String,String> potholeDataMap;
    private Date currentDate;
    private int length;
    private int width;
    private int depth;
    private PotholeType potholeType;
    private double latitude;
    private double longitude;
    private String imageLocationName;
    private double avgSpeed;
    private TrafficVolume trafficVolume;
    private StreetType streetType;
    private SafetyZoneType safetyZoneType;
    private PPRRW potholePosition;
    private RoadMaterial roadMaterial;
    private JunctionType junctionType;
    private WeatherType currentWeatherConditions;
    private boolean existingRepairVisible;
    private PriorityType priority;
    private ImageQualityType imageQuality;
    private SecurityRating securityRating;
    private double dataCompleteness;
    private double dataAccuracy;

    public HashMap<String, String> getPotholeDataMap() {
        return potholeDataMap;
    }

    public void setPotholeDataMap(HashMap<String, String> potholeDataMap) {
        this.potholeDataMap = potholeDataMap;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public PotholeType getPotholeType() {
        return potholeType;
    }

    public void setPotholeType(PotholeType potholeType) {
        this.potholeType = potholeType;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getImageLocationName() {
        return imageLocationName;
    }

    public void setImageLocationName(String imageLocationName) {
        this.imageLocationName = imageLocationName;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public TrafficVolume getTrafficVolume() {
        return trafficVolume;
    }

    public void setTrafficVolume(TrafficVolume trafficVolume) {
        this.trafficVolume = trafficVolume;
    }

    public StreetType getStreetType() {
        return streetType;
    }

    public void setStreetType(StreetType streetType) {
        this.streetType = streetType;
    }

    public SafetyZoneType getSafetyZoneType() {
        return safetyZoneType;
    }

    public void setSafetyZoneType(SafetyZoneType safetyZoneType) {
        this.safetyZoneType = safetyZoneType;
    }

    public PPRRW getPotholePosition() {
        return potholePosition;
    }

    public void setPotholePosition(PPRRW potholePosition) {
        this.potholePosition = potholePosition;
    }

    public RoadMaterial getRoadMaterial() {
        return roadMaterial;
    }

    public void setRoadMaterial(RoadMaterial roadMaterial) {
        this.roadMaterial = roadMaterial;
    }

    public JunctionType getJunctionType() {
        return junctionType;
    }

    public void setJunctionType(JunctionType junctionType) {
        this.junctionType = junctionType;
    }

    public WeatherType getCurrentWeatherConditions() {
        return currentWeatherConditions;
    }

    public void setCurrentWeatherConditions(WeatherType currentWeatherConditions) {
        this.currentWeatherConditions = currentWeatherConditions;
    }

    public boolean isExistingRepairVisible() {
        return existingRepairVisible;
    }

    public void setExistingRepairVisible(boolean existingRepairVisible) {
        this.existingRepairVisible = existingRepairVisible;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public ImageQualityType getImageQuality() {
        return imageQuality;
    }

    public void setImageQuality(ImageQualityType imageQuality) {
        this.imageQuality = imageQuality;
    }

    public SecurityRating getSecurityRating() {
        return securityRating;
    }

    public void setSecurityRating(SecurityRating securityRating) {
        this.securityRating = securityRating;
    }

    public double getDataCompleteness() {
        return dataCompleteness;
    }

    public void setDataCompleteness(double dataCompleteness) {
        this.dataCompleteness = dataCompleteness;
    }

    public double getDataAccuracy() {
        return dataAccuracy;
    }

    public void setDataAccuracy(double dataAccuracy) {
        this.dataAccuracy = dataAccuracy;
    }
}
