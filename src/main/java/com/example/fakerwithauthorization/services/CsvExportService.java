package com.example.fakerwithauthorization.services;

import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import com.opencsv.CSVWriter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
public class CsvExportService {

    private final UsersRepository usersRepository;



    public CsvExportService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


       public static void createCSVFile(List<Users> list) throws IOException {

        MappingStrategy columnStrategy = new HeaderColumnNameMappingStrategy();
        columnStrategy.setType(Users.class);

        try (Writer writer = new FileWriter("listOfUsers.csv")){

            StatefulBeanToCsv<Users> sbc = new StatefulBeanToCsvBuilder<Users>(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withMappingStrategy(columnStrategy)
                    .build();

                    sbc.write(list);


        } catch (CsvRequiredFieldEmptyException e) {
            throw new RuntimeException(e);
        } catch (CsvDataTypeMismatchException e) {
            throw new RuntimeException(e);
        }

       }
}
