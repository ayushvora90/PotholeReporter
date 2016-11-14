package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum SecurityRating {
    EXTREMELY_HIGH("Extremely High"),HIGH("High"),MEDIUM("Medium"),LOW("Low"),EXTREMELY_LOW("Extremely Low");
    private String securityRating;
    SecurityRating(String securityRating){
        this.securityRating = securityRating;
    }

    @Override
    public String toString() {
        return securityRating;
    }
}
