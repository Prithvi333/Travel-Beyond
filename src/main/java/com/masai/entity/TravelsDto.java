package com.masai.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelsDto {

	@NotNull(message = "agentName is mandatory")
	@NotBlank(message = "agentName is mandatory")
	private String agentName;

	@NotNull(message = "address is mandatory")
	@NotBlank(message = "address is mandatory")
	private String address;

	@NotNull(message = "contact is mandatory")
	@NotBlank(message = "contact is mandatory")
	private String contact;
}
