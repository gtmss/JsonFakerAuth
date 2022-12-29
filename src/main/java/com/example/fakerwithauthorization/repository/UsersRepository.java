package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Page findAll(Pageable pageable);

    Optional<Users> findById(Long id);

}
