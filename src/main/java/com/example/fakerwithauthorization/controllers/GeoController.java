package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.Geo;
import com.example.fakerwithauthorization.repository.GeoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geo")
public class GeoController {

    Logger logger = LoggerFactory.getLogger(GeoRepository.class);
    private final GeoRepository geoRepository;

    public GeoController(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    @PostMapping
    public ResponseEntity<?> addGeo(@RequestBody Geo geo){
        logger.debug("Geo object: " + geo);
        return ResponseEntity.ok().body(geoRepository.save(geo).getId());
    }

    @GetMapping
    public ResponseEntity<Page<Geo>> getAll(Pageable pageable){
        logger.debug("Geo pageable argument: " + pageable);
        return ResponseEntity.ok().body(geoRepository.findAll(pageable));
    }

    @PutMapping
    public ResponseEntity<Geo> updateGeo(@RequestBody Geo geo){
        logger.debug("Update Geo object: " + geo);
        return ResponseEntity.ok().body(geoRepository.save(geo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeo(@PathVariable Long id){
        logger.debug("Geo object to delete: " + geoRepository.findById(id));
        geoRepository.deleteById(id);
        logger.debug("Deleted Geo - ID: " + id);
        return ResponseEntity.ok().build();
    }
}
