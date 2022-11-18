package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<Company, Long> {
}
