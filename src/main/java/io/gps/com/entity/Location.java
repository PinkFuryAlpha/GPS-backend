package io.gps.com.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="Latitude")
    private double latitude;

    @Column(name="longitude")
    private double longitude;

    @Column(name="date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Location() {
    }

    public Location(double latitude, double longitude, LocalDateTime date, User user) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = date;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
