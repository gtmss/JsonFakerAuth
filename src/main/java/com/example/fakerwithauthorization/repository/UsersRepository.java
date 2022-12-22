package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Page<Users> findAll(Pageable pageable);

}
