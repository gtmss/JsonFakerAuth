package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.models.Company;
import com.example.fakerwithauthorization.repository.CompanyRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @PostMapping("/add")
    public String addCompany(@RequestBody Company company){
        System.out.println(company.toString());
        companyRepository.save(company);
        return "Added";
    }

    @GetMapping("/get")
    public Iterable<Company> getAllCompanies(){
        return companyRepository.findAll();
    }



}
