package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.PackageBooking;


public interface PackageBookingDao extends JpaRepository<PackageBooking, Integer> {

}
