package com.masai.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;

	@NotBlank(message = "Please provide the feedback details")
	@NotNull(message = "Please provide the feedback details")
	private String feedback;

	@NotBlank(message = "Please provide the rating as well")
	@NotNull(message = "Please provide the rating as well")
	private String rating;

	@CreatedDate
	private LocalDate submitDate;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerFeedback;
}
