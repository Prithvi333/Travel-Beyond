package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Hotel;

public interface HotelDao extends JpaRepository<Hotel, Integer> {

}
