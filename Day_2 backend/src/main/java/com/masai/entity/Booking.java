package com.masai.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	@NotNull(message = "booking type is important")
	private String bookingType;

	private String description;

	@NotNull(message = "give any booking title")
	private String bookingTitle;

	@CreatedDate
	private LocalDate bookingDate;

	@NotNull(message = "mention the number of person that will be in this trip")
	@NotBlank(message = "mention the number of person that will be in this trip")
	private Integer number_Of_Person;

	@JoinColumn(name = "booking_id")
	@ManyToOne
	private Customer customer;

}
