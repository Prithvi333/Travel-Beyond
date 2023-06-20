package com.masai.service;

import java.util.List;

import com.masai.entity.HotelBooking;

public interface HotelBookingOps {
	HotelBooking bookHotel(int customerId, int hotelId, HotelBooking hotelBooking);

	HotelBooking cancleBooking(int id);

	HotelBooking viewBooking(int id);

	List<HotelBooking> viewAllBooking();
}
