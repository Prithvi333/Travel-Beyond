package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.PackageBooking;
import com.masai.entity.Packages;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyPackageBookingListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.PackageBookingNotFoundException;
import com.masai.exception.PackageNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.PackageBookingDao;
import com.masai.repository.PackageDao;

@Service
public class PackageBookingOpsImpl implements PackageBookingOps {

	@Autowired
	PackageBookingDao bd;
	@Autowired
	CustomerDao cd;

	@Autowired
	PackageDao pd;

	@Override
	public PackageBooking makeBooking(int customerId, int packageId) {

		Optional<Customer> customer = cd.findById(customerId);
		Optional<Packages> pakage = pd.findById(packageId);
		if (customer.isEmpty())
			throw new CustomerNotFoundException("Not valid customer to make booking");
		if (pakage.isEmpty())
			throw new PackageNotFoundException("Package not found");

		if (!customer.get().isStatus()) {
			throw new EntityAlreadyAlteredException("Customer is not exist now");
		}
		if (!pakage.get().isStatus()) {
			throw new EntityAlreadyAlteredException("Package is not exist now");
		}
		PackageBooking booking= new PackageBooking();
		booking.setBookingType("online booking");
		booking.setDescription("Booking has been completed enjoy your trip");
		booking.setBookingTitle(pakage.get().getPackageName());
		booking.setNumber_Of_Person(4);
		booking.setPackageName(pakage.get().getPackageName());
		booking.setBookingDate(LocalDate.now());
		booking.setStatus(true);
		booking.setCustomer(customer.get());
		booking.setAPackage(pakage.get());
		pakage.get().getBookingList().add(booking);

		customer.get().getPackageBookings().add(booking);

		return bd.save(booking);

	}

	@Override
	public PackageBooking cancleBooking(int id) {

		Optional<PackageBooking> booking = bd.findById(id);
		if (!booking.isEmpty()) {
			PackageBooking book = booking.get();
			if (!book.isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to cancle already canceld booking");
			}
			book.setStatus(false);
			return bd.save(book);
		}
		throw new PackageBookingNotFoundException("No booking found with the given id to cancle");
	}

	@Override
	public PackageBooking viewBooking(int id) {
		Optional<PackageBooking> booking = bd.findById(id);
		if (!booking.isEmpty()) {
			PackageBooking book = booking.get();
			if (!book.isStatus()) {
				throw new EntityAlreadyAlteredException("Booking is not exist now");
			}
			return book;
		}
		throw new PackageBookingNotFoundException("No booking found with the given id to view");

	}

	@Override
	public List<PackageBooking> viewAllBooking() {

		List<PackageBooking> bookingList = bd.findAll();
		if (!bookingList.isEmpty())
			return bookingList.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyPackageBookingListException("Booking list is empty");
	}

}
