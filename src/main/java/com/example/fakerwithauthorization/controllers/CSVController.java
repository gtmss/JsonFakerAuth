package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.repository.UsersRepository;
import com.example.fakerwithauthorization.services.CsvExportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/export")
public class CSVController {
    private final UsersRepository usersRepository;

    private CsvExportService exportService;

    public CSVController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping
    public void exportUsersCSV() throws IOException {
        CsvExportService.createCSVFile(usersRepository.findAll());
    }
}
