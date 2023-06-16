package com.masai.service;

import java.util.List;

import com.masai.entity.PackageBooking;

public interface PackageBookingOps {

	PackageBooking makeBooking(int custoemrId,int packageId,PackageBooking booking);

	PackageBooking cancleBooking(int id);

	PackageBooking viewBooking(int id);

	List<PackageBooking> viewAllBooking();
}
