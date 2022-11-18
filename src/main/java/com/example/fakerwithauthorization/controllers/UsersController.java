package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.config.AppPropreties;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UsersController {

    private final UsersRepository usersRepository;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    private final AppPropreties appPropreties;

    public UsersController(UsersRepository usersRepository, RestTemplate restTemplate, ObjectMapper objectMapper, AppPropreties appPropreties) {
        this.usersRepository = usersRepository;
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.appPropreties = appPropreties;
    }

    @PostMapping("/add")
    public String addUser( @Valid @RequestBody Users user){
        usersRepository.save(user);
        return "Added";
    }

    @GetMapping("/populate")
    public String populateDb(){
        ResponseEntity<Object[]> response = restTemplate.getForEntity(appPropreties.getUri(), Object[].class);
        List<Users> users = Arrays.stream(response.getBody())
                .map(obj -> objectMapper.convertValue(obj, Users.class))
                .collect(Collectors.toList());
        usersRepository.saveAll(users);
        return"Ok";
    }

    @GetMapping("/get")
    public List<Users> getUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    @PutMapping("/update")
    public String updateUsers(@RequestBody Users users){
        usersRepository.save(users);
        return "Updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUsers(@PathVariable Long id){
        usersRepository.deleteById(id);
        return "Deleted";
    }
}
