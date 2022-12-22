package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.repository.UsersRepository;
import com.example.fakerwithauthorization.services.UsersExportService;
import com.example.fakerwithauthorization.services.csvUtils.CustomMappingStrategy;
import com.example.fakerwithauthorization.services.dto.UsersExportDTO;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/export")
public class CSVController {
    private final UsersRepository usersRepository;

    private UsersExportService exportService;

    public CSVController(UsersRepository usersRepository, UsersExportService exportService) {
        this.usersRepository = usersRepository;
        this.exportService = exportService;
    }

    @GetMapping
    public void exportCSV(HttpServletResponse response) throws Exception {

        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=listOfUsers.csv");

        CustomMappingStrategy<UsersExportDTO> mappingStrategy = new CustomMappingStrategy<>();
        mappingStrategy.setType(UsersExportDTO.class);

        StatefulBeanToCsv<UsersExportDTO> writer = new StatefulBeanToCsvBuilder<UsersExportDTO>(response.getWriter())
                .withMappingStrategy(mappingStrategy)
                .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .withOrderedResults(false)
                .build();


        writer.write(exportService.getUsers());
    }
}
