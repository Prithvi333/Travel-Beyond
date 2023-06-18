package com.masai.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerDto {

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
	@Size(min = 12, max = 12, message = "min 12 and max 12 characters allowed only")
	private String aadharId;

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
}
