package com.masai.service;

import java.util.List;

import com.masai.entity.Hotel;

public interface HotelOps {
	Hotel addHotel(Integer destinationId, Hotel hotel);

//	Hotel addHotel(Hotel hotel);
	Hotel removeHotel(Integer hotelId);

	Hotel searchHotel(int hotelId);

	List<Hotel> viewHotelsBydestinationId(int destinationId);

	List<Hotel> viewAllHotels();
}
