package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.Geo;
import com.example.fakerwithauthorization.repository.GeoRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/geo")
public class GeoController {
    private final GeoRepository geoRepository;

    public GeoController(GeoRepository geoRepository) {
        this.geoRepository = geoRepository;
    }

    @PostMapping("/add")
    public String addGeo(@RequestBody Geo geo){
        geoRepository.save(geo);
        return "saved";
    }

    @GetMapping("/get")
    public Iterable<Geo> getAll(){
        return geoRepository.findAll();
    }

    @PutMapping("/update")
    public Geo updateGeo(@RequestBody Geo geo){
        return geoRepository.save(geo);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGeo(@PathVariable Long id){
        geoRepository.deleteById(id);
        return "Ok";
    }
}
