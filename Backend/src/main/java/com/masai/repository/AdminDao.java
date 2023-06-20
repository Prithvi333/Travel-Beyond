package com.masai.repository;

import com.masai.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminDao extends JpaRepository<Admin,Integer> {


    Optional<Admin> findByEmail(String email);
}
