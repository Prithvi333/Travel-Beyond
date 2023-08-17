package com.masai.service;

import java.util.List;

import com.masai.entity.PaymentDetails;

public interface PaymentDetailsOps {

	PaymentDetails makePayment(int packgageBookingId,int customerId);

	PaymentDetails canclePayment(int paymentId,int customerId);

	List<PaymentDetails> viewPaymentList();

}
