package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum PriorityType {
    EXTREMELY_HIGH("Extremely High"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    EXTREMELY_LOW("Extremely Low");
    private String priorityType;
    PriorityType(String priorityType){
        this.priorityType = priorityType;
    }
}
