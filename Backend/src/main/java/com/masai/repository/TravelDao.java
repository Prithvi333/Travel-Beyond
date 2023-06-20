package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Travels;

public interface TravelDao extends JpaRepository<Travels, Integer> {

}
