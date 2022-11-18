package com.example.fakerwithauthorization.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "geo")
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double lat;

    @NotNull
    private Double lng;

    public Geo() {

    }

    

    public Geo(Long id, Double lat, Double lng) {
        this.id = id;
        this.lat = lat;
        this.lng = lng;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
