package com.example.ayushvora.potholereporter.types;

/**
 * Created by ayushvora on 10/23/16.
 */

public enum WeatherType {
    SUNNY("Sunny",0.25),WINDY("Windy",0.50),RAINY("Rainy",0.75),SNOWY("Snowy",1.00);
    private String weather;
    private double valueWt;
    WeatherType(String weather, double valueWt){
        this.weather = weather;
        this.valueWt = valueWt;
    }

    @Override
    public String toString() {
        return weather;
    }

    public static double getValueWt(String weatherType) {
        if(weatherType.equals(WeatherType.RAINY.toString())){
            return WeatherType.RAINY.valueWt;
        } else if(weatherType.equals(WeatherType.SNOWY.toString())){
            return WeatherType.SNOWY.valueWt;
        } else if(weatherType.equals(WeatherType.WINDY.toString())){
            return WeatherType.WINDY.valueWt;
        } else if(weatherType.equals(WeatherType.SUNNY.toString())){
            return WeatherType.SUNNY.valueWt;
        } else {
            return 0;
        }

    }




}
