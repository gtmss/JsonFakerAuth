package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
