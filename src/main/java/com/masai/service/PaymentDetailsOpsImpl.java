package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.HotelBooking;
import com.masai.entity.PaymentDetails;
import com.masai.exception.EmptyPaymentDetialsListException;
import com.masai.exception.HotelBookingNotFoundException;
import com.masai.exception.PaymentDetailsNotFoundException;
import com.masai.repository.HotelBookingDao;
import com.masai.repository.PaymentDetailsDao;
import org.springframework.stereotype.Service;

@Service
public class PaymentDetailsOpsImpl implements PaymentDetailsOps {

	@Autowired
	HotelBookingDao hb;
	@Autowired
	PaymentDetailsDao pd;

	@Override
	public PaymentDetails makePayment(int hotelBookingId, PaymentDetails paymentDetails) {

		Optional<HotelBooking> hotelBooking = hb.findById(hotelBookingId);

		if (!hotelBooking.isEmpty()) {
			paymentDetails.setStatus(true);
			paymentDetails.setBookingId(hotelBookingId);
			hotelBooking.get().getPayment().add(paymentDetails);
			return pd.save(paymentDetails);
		}
		throw new HotelBookingNotFoundException("No hotel booking found with the given id");

	}

	@Override
	public PaymentDetails canclePayment(int paymentId) {

		Optional<PaymentDetails> paymentDetails = pd.findById(paymentId);
		if (!paymentDetails.isEmpty()) {
			paymentDetails.get().setStatus(false);
			return pd.save(paymentDetails.get());
		}
		throw new PaymentDetailsNotFoundException("Payment details not found");
	}

	@Override
	public List<PaymentDetails> viewPaymentList() {

		List<PaymentDetails> paymentDetails = pd.findAll();
		if (!paymentDetails.isEmpty())
			return paymentDetails;
		throw new EmptyPaymentDetialsListException("Payment details list is empty");
	}

}
