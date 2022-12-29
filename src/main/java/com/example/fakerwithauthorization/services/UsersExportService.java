package com.example.fakerwithauthorization.services;

import com.example.fakerwithauthorization.models.Address;
import com.example.fakerwithauthorization.models.Company;
import com.example.fakerwithauthorization.models.Geo;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import com.example.fakerwithauthorization.services.dto.UsersExportDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersExportService {
    private final UsersRepository usersRepository;


    public UsersExportService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }



    public List<UsersExportDTO> getUsers() {
        List<UsersExportDTO> userExportDTOList = new ArrayList<>();

        usersRepository.findAll().forEach(user -> userExportDTOList.add(
                new UsersExportDTO(user.getName(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getAddress().getStreet(),
                        user.getAddress().getSuite(),
                        user.getAddress().getCity(),
                        user.getAddress().getZipcode(),
                        user.getAddress().getGeo().getLat().toString(),
                        user.getAddress().getGeo().getLng().toString(),
                        user.getPhone(),
                        user.getWebsite()
                ))

        );
        return userExportDTOList;
    }

    public List<Users> getUsersFromUsersDto(List<UsersExportDTO> usersExportDTO) {
        List<Users> usersList = new ArrayList<>();
        // skip 1 because first column are the names
        usersExportDTO.stream().skip(1).forEach(userExportDTO -> usersList.add(
                new Users(
                        userExportDTO.getName(),
                        userExportDTO.getUsername(),
                        userExportDTO.getEmail(),
                        userExportDTO.getPhone(),
                        userExportDTO.getWebsite(),
                        new Address(
                                userExportDTO.getStreet(),
                                userExportDTO.getCity(),
                                userExportDTO.getSuite(),
                                userExportDTO.getZipcode(),
                                new Geo(
                                        Double.parseDouble(userExportDTO.getLat()),
                                        Double.parseDouble(userExportDTO.getLng())
                                )
                        ),
                        new Company(
                                "company_name",
                                "catch_phrase",
                                "bs"

                        )

                )
        ));

        return usersList;

    }
}
