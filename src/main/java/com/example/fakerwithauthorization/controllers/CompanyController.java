package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.Company;
import com.example.fakerwithauthorization.repository.CompanyRepository;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping
    public ResponseEntity<Void> addCompany(@RequestBody Company company){
        companyRepository.save(company);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiImplicitParam(name = "Authorization", value = "Access Token", required = true, paramType = "header", dataTypeClass = String.class, example = "Bearer access_token")
    public ResponseEntity<Page<Company>> getAllCompanies(Pageable pageable){
        return ResponseEntity.ok().body(companyRepository.findAll(pageable));
    }



}
