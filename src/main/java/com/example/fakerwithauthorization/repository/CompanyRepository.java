package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Long> {
}
