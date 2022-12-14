package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.Company;
import com.example.fakerwithauthorization.repository.CompanyRepository;
import io.swagger.annotations.ApiImplicitParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {
    Logger logger = LoggerFactory.getLogger(CompanyController.class);
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addCompany(@RequestBody Company company){
        logger.debug("Company added: " + companyRepository.save(company));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<Company>> getAllCompanies(Pageable pageable){
        logger.debug("Company pageable argument: " + pageable);
        return ResponseEntity.ok().body(companyRepository.findAll(pageable));
    }



}
