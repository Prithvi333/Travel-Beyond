package com.masai.service;

import java.util.List;

import com.masai.entity.PackageBooking;

public interface BookingOps {

	PackageBooking makeBooking(int custoemrId,PackageBooking booking);

	PackageBooking cancleBooking(int id);

	PackageBooking viewBooking(int id);

	List<PackageBooking> viewAllBooking();
}
