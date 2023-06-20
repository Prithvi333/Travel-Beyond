package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.HotelBooking;

public interface HotelBookingDao extends JpaRepository<HotelBooking, Integer> {

	
}
