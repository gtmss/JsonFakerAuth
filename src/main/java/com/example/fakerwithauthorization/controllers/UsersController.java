package com.example.fakerwithauthorization.controllers;

import com.example.fakerwithauthorization.config.AppPropreties;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Logger logger = LoggerFactory.getLogger(UsersController.class);
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
    public ResponseEntity<Users> addUser(@Valid @RequestBody Users user){
        logger.debug("Employee - Users object: " + user);
        return ResponseEntity.ok().body(usersRepository.save(user));
    }

    @GetMapping("/populate")
    public ResponseEntity<Void> populateDb(){
        ResponseEntity<Object[]> response = restTemplate.getForEntity(appPropreties.getUri(), Object[].class);
        List<Users> users = Arrays.stream(response.getBody())
                .map(obj -> objectMapper.convertValue(obj, Users.class))
                .collect(Collectors.toList());
        usersRepository.saveAll(users);

        logger.debug("Source URI: " + appPropreties.getUri());
        logger.debug("Response from URI" + response);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Users>> getUsers(Pageable pageable) {
        logger.debug("Users pageable argument: " + pageable);
        return ResponseEntity.ok().body((usersRepository.findAll(pageable)));
    }

    @PutMapping
    public ResponseEntity<Users> updateUsers(@RequestBody Users users){
        logger.debug("Update Users object: " + users);
        return ResponseEntity.ok().body(usersRepository.save(users));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id){
        logger.debug("Employee - Users object to delete:" + usersRepository.findById(id));
        usersRepository.deleteById(id);
        logger.debug("Deleted Users - ID: " + id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
