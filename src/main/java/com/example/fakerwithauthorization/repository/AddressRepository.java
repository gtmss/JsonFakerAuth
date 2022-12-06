package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Long> {
    Page<Address> findAll(Pageable pageable);
}
