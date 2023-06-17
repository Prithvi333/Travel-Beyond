package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Destination;
import com.masai.entity.Hotel;
import com.masai.exception.DestinationNotFoundException;
import com.masai.exception.EmptyHotelListException;
import com.masai.exception.HotelNotFoundException;
import com.masai.repository.DestinationDao;
import com.masai.repository.HotelDao;
import org.springframework.stereotype.Service;

@Service
public class HotelOpsImpl implements HotelOps{

	@Autowired
	DestinationDao dd;
	@Autowired
	HotelDao hd;

//	@Override
//	public Hotel addHotel(int destinationId, Hotel hotel) {
//
//		Optional<Destination> destination = dd.findById(destinationId);
//		if (destination.isPresent()) {
//			Destination dest = destination.get();
//			hotel.setDestination(dest);
//			hotel.setStats(true);
//			dest.getHotels().add(hotel);
//			return hd.save(hotel);
//		}
//		throw new DestinationNotFoundException("No destination found");
//	}

	@Override
	public Hotel addHotel(Integer destinationId, Hotel hotel) {

//		Optional<Destination> destination = dd.findById(destinationId);
//		if(destination.isEmpty()){
//			throw new DestinationNotFoundException("No destination available with this id");
//		}
//		Optional<Hotel> hotelOptional = hd.findById(hotel.getHotelId());
//		if(hotelOptional.isPresent()){
//			throw new DestinationNotFoundException(" hotel already available");
//
//		}
//
//		hotel.setDestination(destination.get());
//		destination.get().getHotels().add(hotel);
//		return hd.save(hotel);


		Destination destination = dd.findById(destinationId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid destination ID"));


		Optional<Hotel> hotelOptional = hd.findById(hotel.getHotelId());
		if(hotelOptional.isPresent()){
			throw new DestinationNotFoundException(" hotel already available");

		}
		hotel.setDestination(destination);

		return hd.save(hotel);





	}

	@Override
	public Hotel removeHotel(Integer hotelId) {

		Optional<Hotel> hotel = hd.findById(hotelId);
		if (!hotel.isEmpty()) {
			hotel.get().setStats(false);
			return hd.save(hotel.get());
		}
		throw new HotelNotFoundException("Hotel not found with given id");
	}

	@Override
	public Hotel searchHotel(int hotelId) {
		Optional<Hotel> hotel = hd.findById(hotelId);
		if (!hotel.isEmpty()) {
			return hotel.get();
		}
		throw new HotelNotFoundException("Hotel not found to view");
	}

	@Override
	public List<Hotel> viewHotelsBydestinationId(int destinationId) {

		Optional<Destination> destination = dd.findById(destinationId);
		if (!destination.isEmpty()) {
			List<Hotel> hotels = destination.get().getHotels();
			if (!hotels.isEmpty())
				return hotels;
			throw new EmptyHotelListException("Empty hotel list");
		}
		throw new DestinationNotFoundException("No destination is found with the given id");
	}

	@Override
	public List<Hotel> viewAllHotels() {
		List<Hotel> hotels = hd.findAll();
		if (!hotels.isEmpty())
			return hotels;
		throw new EmptyHotelListException("Hotels list is empty");
	}

}
