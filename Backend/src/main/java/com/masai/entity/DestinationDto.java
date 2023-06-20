package com.masai.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DestinationDto {
	@NotBlank(message = "Please provide the destination name")
	@NotNull(message = "Please provide the destination name")
	private String name;

	@NotNull(message = "Please choose desEnvironment")
	private DesEnvironment desEnvironment;
}
