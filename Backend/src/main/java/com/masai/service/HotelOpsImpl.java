package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Destination;
import com.masai.entity.Hotel;
import com.masai.exception.DestinationNotFoundException;
import com.masai.exception.EmptyHotelListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.HotelNotFoundException;
import com.masai.repository.DestinationDao;
import com.masai.repository.HotelDao;
import org.springframework.stereotype.Service;

@Service
public class HotelOpsImpl implements HotelOps {

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

//	Here admin is allowed to add the hotel based destination and hotel availablity
	@Override
	public Hotel addHotel(Integer destinationId, Hotel hotel) {

//		Optional<Destination> destination = dd.findById(destinationId);
//		if (destination.isEmpty()) {
//			throw new DestinationNotFoundException("No destination available with this id");
//		}
//		Optional<Hotel> hotelOptional = hd.findById(hotel.getHotelId());
//		if (hotelOptional.isPresent()) {
//			throw new DestinationNotFoundException(" Hotel already available");
//
//		}
//		hotel.setStats(true);
//		hotel.setDestination(destination.get());
//		destination.get().getHotels().add(hotel);
//		return hd.save(hotel);
//		System.out.println(destinationId);
		Destination destination = dd.findById(destinationId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid destination ID"));

		if (!destination.isStatus()) {
			throw new EntityAlreadyAlteredException("Destination is not available");
		}
//		Optional<Hotel> hotelOptional = hd.findById(hotel.getHotelId());
//		if (hotelOptional.isPresent()) {
//			throw new DestinationNotFoundException(" Hotel already available");
//
//		}
		hotel.setStats(true);
		hotel.setDestination(destination);

		return hd.save(hotel);

	}

// Using this method we can remover hotel based on its id
	@Override
	public Hotel removeHotel(Integer hotelId) {

		Optional<Hotel> hotel = hd.findById(hotelId);
		if (!hotel.isEmpty()) {
			if (!hotel.get().isStats()) {
				throw new EntityAlreadyAlteredException("Unable to remove already deleted hotel");
			}
			hotel.get().setStats(false);
			return hd.save(hotel.get());
		}
		throw new HotelNotFoundException("Hotel not found with given id");
	}
// Method that is used to find the hotel based on its id applicable for both customer and admin
	@Override
	public Hotel searchHotel(int hotelId) {
		Optional<Hotel> hotel = hd.findById(hotelId);
		if (!hotel.isEmpty()) {
			if (!hotel.get().isStats()) {
				throw new EntityAlreadyAlteredException("Hotel is not exist now");
			}
			return hotel.get();
		}
		throw new HotelNotFoundException("Hotel not found to view");
	}
// If user have only destination id he can able to search hotel based on it
	@Override
	public List<Hotel> viewHotelsBydestinationId(int destinationId) {

		Optional<Destination> destination = dd.findById(destinationId);
		if (!destination.get().isStatus()) {
			throw new EntityAlreadyAlteredException("This destination is not available now");
		}
		if (!destination.isEmpty()) {
			List<Hotel> hotels = destination.get().getHotels();
			if (!hotels.isEmpty())
				return hotels.stream().filter(a -> a.isStats()).toList();
			throw new EmptyHotelListException("Empty hotel list");
		}
		throw new DestinationNotFoundException("No destination is found with the given id");
	}
// This method is used to show the all hotels at a time
	@Override
	public List<Hotel> viewAllHotels() {
		List<Hotel> hotels = hd.findAll();
		if (!hotels.isEmpty())
			return hotels.stream().filter(a -> a.isStats()).toList();
		throw new EmptyHotelListException("Hotels list is empty");
	}

}
