package com.example.fakerwithauthorization.models;

import com.opencsv.bean.CsvBindByName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "geo")
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @CsvBindByName(column = "lat")
    private Double lat;

    @NotNull
    @CsvBindByName(column = "lng")
    private Double lng;

    public Geo() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Objects.equals(id, geo.id) && Objects.equals(lat, geo.lat) && Objects.equals(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lat, lng);
    }

    @Override
    public String toString() {
        return "Geo{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
