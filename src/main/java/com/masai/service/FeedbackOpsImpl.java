package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.Feedback;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyFeedbackListException;
import com.masai.exception.FeedbackNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.FeedbackDao;

@Service
public class FeedbackOpsImpl implements FeedbackOps {

	@Autowired
	FeedbackDao fd;
	@Autowired
	CustomerDao cd;

	@Override
	public Feedback addFeedback(int customerId, Feedback feedback) {
		Optional<Customer> customer = cd.findById(customerId);

		if (!customer.isEmpty()) {
			customer.get().getFeedback().add(feedback);
			feedback.setCustomerFeedback(customer.get());
			return feedback;
		}
		throw new CustomerNotFoundException("Customer not valid");
	}

	@Override
	public Feedback findFeedbackbyFeedbackId(int feedbackId) {

		Optional<Feedback> feedback = fd.findById(feedbackId);
		if (!feedback.isEmpty())
			return feedback.get();
		throw new FeedbackNotFoundException("Feedback not found with the  given id");
	}

	@Override
	public Feedback findFeedbackbyCustomerId(int customerId) {
		Optional<Customer> customer = cd.findById(customerId);
		if (!customer.isEmpty()) {
			Optional<Feedback> feedback = fd.findById(customer.get().getCustomerId());

			if (!feedback.isEmpty())
				return feedback.get();
			throw new FeedbackNotFoundException("Customer gave no feedback yet");

		}
		throw new CustomerNotFoundException("Customer not found with the  given id");
	}

	@Override
	public List<Feedback> getAllFeedback() {

		List<Feedback> feedBackList = fd.findAll();
		if (!feedBackList.isEmpty()) {
			return feedBackList;
		}
		throw new EmptyFeedbackListException("Feedback list is empty");
	}

}
