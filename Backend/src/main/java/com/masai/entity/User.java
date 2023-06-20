package com.masai.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@NotBlank(message = "userId is mandatory")
	private Integer userId;

	@NotBlank(message = "userType is mandatory")

	private UserType userType;

	@NotBlank(message = "password is mandatory")
	@Size(min = 6, max = 10, message = "min 6 and max 10 is only allowed")
	private String password;

}
