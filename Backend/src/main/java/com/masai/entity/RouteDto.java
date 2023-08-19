package com.masai.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {
	@NotBlank(message = "Please provide the starting details")
	@NotNull(message = "Please provide the starting details")
	private String routeFrom;

	@NotBlank(message = "Please provide the ending details")
	@NotNull(message = "Please provide the ending details")
	private String routeTo;

	@NotBlank(message = "Please provide the departureTime")
	@NotNull(message = "Please provide the departureTime")

	private String departureTime;

	@NotBlank(message = "Please provide the arrivalTime")
	@NotNull(message = "Please provide the arrivalTime")
	private String arrivalTime;
}
