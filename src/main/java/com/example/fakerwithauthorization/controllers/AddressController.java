package com.example.fakerwithauthorization.controllers;


import com.example.fakerwithauthorization.models.Address;
import com.example.fakerwithauthorization.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    Logger logger = LoggerFactory.getLogger(AddressRepository.class);
    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @PostMapping
    public ResponseEntity<Address> addAdress(@RequestBody Address address){
        logger.debug("Adress object: " + address);
        return ResponseEntity.ok().body(addressRepository.save(address));
    }

    @GetMapping
    public ResponseEntity<Page<Address>> getAll(Pageable pageable){
        logger.debug("Address pageable argument: " + pageable);
        return  ResponseEntity.ok().body(addressRepository.findAll(pageable));
    }

    @PutMapping
    public ResponseEntity<Address> updateAdress(@RequestBody Address address){
        logger.debug("Adress object, update: " + address);
        return ResponseEntity.ok().body(addressRepository.save(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdress(@PathVariable Long id){
        logger.debug("Geo object to delete: " + addressRepository.findById(id));
        addressRepository.deleteById(id);
        logger.debug("Deleted Geo - ID: " + id);
        return ResponseEntity.ok().build();
    }

}
