package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Destination;

public interface DestinationDao extends JpaRepository<Destination, Integer> {

}
