package io.gps.com.dto;

import java.time.LocalDateTime;

public class LocationDTO {

    private double latitude;
    private double longitude;
    private LocalDateTime date;
    private int userId;

    public LocationDTO() {
    }

    public LocationDTO(double latitude, double longitude,LocalDateTime date, int userId) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.userId = userId;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
