package com.masai.service;

import java.util.List;

import com.masai.entity.PaymentDetails;

public interface PaymentDetailsOps {

	PaymentDetails makePayment(int packgageBookingId, PaymentDetails paymentDetails);

	PaymentDetails canclePayment(int paymentId);

	List<PaymentDetails> viewPaymentList();

}
