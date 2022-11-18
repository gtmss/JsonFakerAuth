package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
