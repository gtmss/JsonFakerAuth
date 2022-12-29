package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.repository.UsersRepository;
import com.example.fakerwithauthorization.services.UsersExportService;
import com.example.fakerwithauthorization.services.csvUtils.CustomMappingStrategy;
import com.example.fakerwithauthorization.services.dto.UsersExportDTO;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@RestController
@RequestMapping("/csv")
public class CSVController {
    private final UsersRepository usersRepository;

    private UsersExportService exportService;

    private final Logger logger;

    public CSVController(UsersRepository usersRepository, UsersExportService exportService, Logger logger) {
        this.usersRepository = usersRepository;
        this.exportService = exportService;
        this.logger = logger;
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

    @PostMapping("/upload-csv")
    public void uploadCSVFile(@RequestParam("file") MultipartFile file ){
        if(file.isEmpty()){
            logger.error("file is empty");
        } else{
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
                List<UsersExportDTO> userExportDTOList = new CsvToBeanBuilder(reader)
                        .withType(UsersExportDTO.class)
                        .build()
                        .parse();
                logger.info("received list of users from file");
                usersRepository.saveAll(exportService.getUsersFromUsersDto(userExportDTOList));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
