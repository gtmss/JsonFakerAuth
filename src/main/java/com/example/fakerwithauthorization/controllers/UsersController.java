package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.config.AppPropreties;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
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

    @PostMapping
    public ResponseEntity<Void> addUser( @Valid @RequestBody Users user){
        usersRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/populate")
    public ResponseEntity<Void> populateDb(){
        ResponseEntity<Object[]> response = restTemplate.getForEntity(appPropreties.getUri(), Object[].class);
        List<Users> users = Arrays.stream(response.getBody())
                .map(obj -> objectMapper.convertValue(obj, Users.class))
                .collect(Collectors.toList());
        usersRepository.saveAll(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Users>> getUsers(Pageable pageable) {
        return ResponseEntity.ok().body((usersRepository.findAll(pageable)));
    }

    @PutMapping
    public ResponseEntity<Void> updateUsers(@RequestBody Users users){
        usersRepository.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id){
        usersRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
