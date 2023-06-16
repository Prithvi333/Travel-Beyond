package com.masai.service;

import java.util.List;

import com.masai.entity.Hotel;

public interface HotelOps {
	Hotel addHotel(int destinationId, Hotel hotel);

	Hotel removeHotel(int hotelId);

	Hotel searchHotel(int hotelId);

	List<Hotel> viewHotelsBydestinationId(int destinationId);

	List<Hotel> viewAllHotels();
}
