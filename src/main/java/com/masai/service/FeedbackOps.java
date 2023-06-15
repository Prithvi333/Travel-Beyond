package com.masai.service;

import java.util.List;

import com.masai.entity.Feedback;

public interface FeedbackOps {

	Feedback addFeedback(int customerId, Feedback feedback);

	Feedback findFeedbackbyFeedbackId(int feedbackId);

	Feedback findFeedbackbyCustomerId(int customerId);

	List<Feedback> getAllFeedback();

}
