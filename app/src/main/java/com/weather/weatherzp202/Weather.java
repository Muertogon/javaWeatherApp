package com.weather.weatherzp202;

import java.io.Serializable;

//konstruktorius
public class Weather implements Serializable {
    final String location;
    final int conditionId;
    final String weatherCondition;
    final double tempKelvin;

    public Weather(final String location,
                    final int conditionId,
                    final String weatherCondition,
                    final double tempKelvin){
        this.location = location;
        this.conditionId = conditionId;
        this.weatherCondition = weatherCondition;
        this.tempKelvin = tempKelvin;
    }
    //funkcija pakeicianti Kelvinus i Celsiju
    public int getTempFahrenheit() {
        return (int) (tempKelvin - 273.15);
    }
}