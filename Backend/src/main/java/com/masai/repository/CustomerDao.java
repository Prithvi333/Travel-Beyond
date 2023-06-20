package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Customer;

import java.util.Optional;

public interface CustomerDao extends JpaRepository<Customer, Integer> {


    Optional<Customer> findByEmail(String email);

}
