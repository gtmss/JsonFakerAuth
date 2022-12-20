package com.example.fakerwithauthorization.repository;

import com.example.fakerwithauthorization.models.User;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.services.dto.CSVExportDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Page<Users> findAll(Pageable pageable);

}
