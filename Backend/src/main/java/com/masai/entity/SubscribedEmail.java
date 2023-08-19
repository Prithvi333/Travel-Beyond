package com.masai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
public class SubscribedEmail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer subsId;
	@Email(message = "Valid email id")
	@NotNull(message = "Value should not be null")
	String email;
	
}
