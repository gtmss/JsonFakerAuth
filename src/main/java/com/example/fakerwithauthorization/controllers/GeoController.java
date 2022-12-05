package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.Geo;
import com.example.fakerwithauthorization.repository.GeoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geo")
public class GeoController {
    private final GeoRepository geoRepository;

    public GeoController(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    @PostMapping
    public ResponseEntity<?> addGeo(@RequestBody Geo geo){
        return ResponseEntity.ok().body(geoRepository.save(geo).getId());
    }

    @GetMapping
    public ResponseEntity<List<Geo>> getAll(){
        return ResponseEntity.ok().body(geoRepository.findAll());
    }

    @PutMapping
    public ResponseEntity<Geo> updateGeo(@RequestBody Geo geo){
        return ResponseEntity.ok().body(geoRepository.save(geo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeo(@PathVariable Long id){
        geoRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
