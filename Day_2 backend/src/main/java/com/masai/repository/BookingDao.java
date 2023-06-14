package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Booking;

public interface BookingDao extends JpaRepository<Booking, Integer> {

}
