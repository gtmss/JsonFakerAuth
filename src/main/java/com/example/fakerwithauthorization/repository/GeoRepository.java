package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Geo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeoRepository extends JpaRepository<Geo, Long> {

}
