package com.tqs1.tqs1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private double latitude;
    private double longitude;
    private String timezone;
    @OneToOne
    private Daily daily;

    public Weather() {
    }

    public Weather(double latitude, double longitude) {

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Weather(double latitude, double longitude, String timezone, Daily daily) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.daily = daily;

    }

    public double getLatitude(){
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude(){
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone(){
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Daily getDaily(){ return daily;}

    public void setData(Daily daily) {
        this.daily = daily;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezone='" + timezone + '\'' +
                ", Daily=" + daily +
                "}";
    }
}