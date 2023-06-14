package com.masai.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	@NotBlank(message = "Please provide the customer name")
	@NotNull(message = "Please provide the customer name")
	private String customerName;

	@NotBlank(message = "Please provide the customer password")
	@NotNull(message = "Please provide the customer password")
	@Size(min = 6, max = 10, message = "min 6 and max 10 characters allowed only")
	private String customerPassword;

	@NotBlank(message = "Please provide the customer password")
	@NotNull(message = "Please provide the customer password")
	@Size(min = 6, message = "min 6 is characters required")
	private String address;

	@NotBlank(message = "Please provide the customer password")
	@NotNull(message = "Please provide the customer password")
	@Size(min = 6, max = 10, message = "min 15 and max 15 characters allowed only")
	private String aadharId;

	@NotBlank(message = "Please select gender")
	@NotNull(message = "Please select gender")
	private Gender gender;

	@NotBlank(message = "Please select your country")
	@NotNull(message = "Please select your country")
	private String country;

	@NotBlank(message = "Please provide the customer password")
	@NotNull(message = "Please provide the customer password")
	@Size(min = 10, max = 10, message = "min 10 and max 10 characters allowed only")
	private String mobileNo;

	@Email(message = "email is not in correct format")
	@NotBlank(message = "Please provide the  email")
	@NotNull(message = "Please provide the  email")
	private String email;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	
	private List<Booking> booking;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Feedback> feedback = new ArrayList<>();;
}
