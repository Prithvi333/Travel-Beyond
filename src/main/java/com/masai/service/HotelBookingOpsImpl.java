package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Customer;
import com.masai.entity.Hotel;
import com.masai.entity.HotelBooking;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyHotelBookingListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.HotelBookingNotFoundException;
import com.masai.exception.HotelNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.HotelBookingDao;
import com.masai.repository.HotelDao;

public class HotelBookingOpsImpl implements HotelBookingOps {

	@Autowired
	CustomerDao cd;
	@Autowired
	HotelDao hd;
	@Autowired
	HotelBookingDao hbd;

	@Override
	public HotelBooking bookHotel(int customerId, int hotelId, HotelBooking hotelBooking) {

		Optional<Customer> customer = cd.findById(customerId);
		Optional<Hotel> hotel = hd.findById(hotelId);
		if (customer.isEmpty())
			throw new CustomerNotFoundException("Customer not found");
		if (hotel.isEmpty())
			throw new HotelNotFoundException("Hotel not found");
		hotelBooking.setStatus(true);
		hotelBooking.setCustomerBooking(customer.get());
		hotelBooking.setHotel(hotel.get());
		customer.get().getHotelBookings().add(hotelBooking);
		return hbd.save(hotelBooking);
	}

	@Override
	public HotelBooking cancleBooking(int id) {

		Optional<HotelBooking> hotelBooking = hbd.findById(id);
		if (!hotelBooking.isEmpty()) {
			if (!hotelBooking.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Booking is already cancled");
			}
			hotelBooking.get().setStatus(false);
			return hbd.save(hotelBooking.get());
		}
		throw new HotelBookingNotFoundException("No hotel booking found with the given id to cancle");
	}

	@Override
	public HotelBooking viewBooking(int id) {

		Optional<HotelBooking> hotelBooking = hbd.findById(id);
		if (!hotelBooking.isEmpty()) {
			return hotelBooking.get();
		}
		throw new HotelBookingNotFoundException("No hotel booking found with the given id to view");
	}

	@Override
	public List<HotelBooking> viewAllBooking() {

		List<HotelBooking> hotelBookingList = hbd.findAll();
		if (!hotelBookingList.isEmpty())
			return hotelBookingList.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyHotelBookingListException("Hotel booking list is empty");
	}

}
