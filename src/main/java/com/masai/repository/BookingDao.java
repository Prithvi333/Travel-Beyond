package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.PackageBooking;

public interface BookingDao extends JpaRepository<PackageBooking, Integer> {

}
