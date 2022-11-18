package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users, Long> {
}
