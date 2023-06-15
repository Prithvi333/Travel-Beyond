package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.PackageBooking;
import com.masai.exception.BookingNotFoundException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyBookingListException;
import com.masai.repository.BookingDao;
import com.masai.repository.CustomerDao;

@Service
public class BookingOpsImpl implements BookingOps {

	@Autowired
	BookingDao bd;
	@Autowired
	CustomerDao cd;

	@Override
	public PackageBooking makeBooking(int id, PackageBooking booking) {

		Optional<Customer> customer = cd.findById(id);
		if (!customer.isEmpty()) {
			customer.get().getPackageBookings().add(booking);
			booking.setStatus(true);
			booking.setCustomer(customer.get());
			return bd.save(booking);
		}

		throw new CustomerNotFoundException("Customer is not valid for booking");
	}

	@Override
	public PackageBooking cancleBooking(int id) {

		Optional<PackageBooking> booking = bd.findById(id);
		if (!booking.isEmpty()) {
			PackageBooking book = booking.get();
			book.setStatus(false);
			return book;
		}
		throw new BookingNotFoundException("No booking found with the given id to cancle");
	}

	@Override
	public PackageBooking viewBooking(int id) {
		Optional<PackageBooking> booking = bd.findById(id);
		if (!booking.isEmpty()) {
			PackageBooking book = booking.get();
			return book;
		}
		throw new BookingNotFoundException("No booking found with the given id to view");

	}

	@Override
	public List<PackageBooking> viewAllBooking() {

		List<PackageBooking> bookingList = bd.findAll();
		if (!bookingList.isEmpty())
			return bookingList;
		throw new EmptyBookingListException("Booking list is empty");
	}

}
