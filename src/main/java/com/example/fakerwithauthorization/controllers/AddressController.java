package com.example.fakerwithauthorization.controllers;


import com.example.fakerwithauthorization.models.Address;
import com.example.fakerwithauthorization.repository.AddressRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @PostMapping("/add")
    public String addAdress(@RequestBody Address address){
        System.out.println(address.toString());
        addressRepository.save(address);
        return "Adress aded";
    }

    @GetMapping("/get")
    public Iterable<Address> getAll(){
        return addressRepository.findAll();
    }

    @PutMapping("/update")
    public Address updateAdress(@RequestBody Address address){
        addressRepository.save(address);
        return address;
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAdress(@PathVariable Long id){
        addressRepository.deleteById(id);
        return "Deleted";
    }

}
