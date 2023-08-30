package com.masai.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.PackageBooking;
import com.masai.entity.PaymentDetails;
import com.masai.entity.PaymentStatus;
import com.masai.entity.PaymentType;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyPaymentDetialsListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.PackageBookingNotFoundException;
import com.masai.exception.PaymentDetailsNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.PackageBookingDao;
import com.masai.repository.PaymentDetailsDao;

@Service
public class PaymentDetailsOpsImpl implements PaymentDetailsOps {

	@Autowired
	PackageBookingDao pbd;
	@Autowired
	PaymentDetailsDao pd;
	@Autowired
	CustomerDao cd;

//	@Override
//	public PaymentDetails makePayment(int hotelBookingId, PaymentDetails paymentDetails) {
//
//		Optional<HotelBooking> hotelBooking = hb.findById(hotelBookingId);
//
//		if (!hotelBooking.isEmpty()) {
//			if (!hotelBooking.get().isStatus()) {
//				throw new EntityAlreadyAlteredException("Hotel not exist now");
//			}
//            
//			paymentDetails.setStatus(true);
//			paymentDetails.setBookingId(hotelBookingId);
//			hotelBooking.get().getPayment().add(paymentDetails);
//			return pd.save(paymentDetails);
//		}
//		throw new HotelBookingNotFoundException("No hotel booking found with the given id");
//
//	}
	
// By using this method customer can make the payment after booking the package
	@Override
	public PaymentDetails makePayment(int packgageBookingId, int customerId) {

		Optional<PackageBooking> packageBooking = pbd.findById(packgageBookingId);
		Optional<Customer> customer = cd.findById(customerId);
		if (customer.isEmpty())
			throw new CustomerNotFoundException("Please enter valid customer id");

		if (!packageBooking.isEmpty()) {
			if (!packageBooking.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Package Booking not exist now");
			}
			PaymentDetails paymentDetails = new PaymentDetails();
			paymentDetails.setPaymentType(PaymentType.DEBIT_CARD);
			paymentDetails.setPaymentStatus(PaymentStatus.COMPLETED);
			paymentDetails.setCustomerId(customerId);
			paymentDetails.setLocalDate(LocalDate.now());
			paymentDetails.setPaymentMoney(packageBooking.get().getAPackage().getPackageCost());
			paymentDetails.setStatus(true);
			paymentDetails.setBookingId(packgageBookingId);
			packageBooking.get().getPayment().add(paymentDetails);
			return pd.save(paymentDetails);
		}
		throw new PackageBookingNotFoundException("No hotel booking found with the given id");

	}
// Using this method customer can cancle the payment and he/she will be refunded soon
	@Override
	public PaymentDetails canclePayment(int paymentId,int customerId) {

		Optional<PaymentDetails> paymentDetails = pd.findById(paymentId);
		if (!paymentDetails.isEmpty()) {
			if (!paymentDetails.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to cancle already cancled payment");
			}
			if(paymentDetails.get().getCustomerId()!=customerId)
				throw new PaymentDetailsNotFoundException("Not valid customer");
			paymentDetails.get().setStatus(false);
			return pd.save(paymentDetails.get());
		}
		throw new PaymentDetailsNotFoundException("Payment details not found");
	}

//	Using the method admin can see all the payments that are done
	@Override
	public List<PaymentDetails> viewPaymentList() {

		List<PaymentDetails> paymentDetails = pd.findAll();
		if (!paymentDetails.isEmpty())
			return paymentDetails.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyPaymentDetialsListException("Payment details list is empty");
	}

}
