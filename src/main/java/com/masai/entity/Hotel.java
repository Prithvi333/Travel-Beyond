package com.masai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer hotelId;

	@NotBlank(message = "Please provide the hotel name")
	@NotNull(message = "Please provide the hotel name")
	private String hotelName;

	@NotNull(message = "Please provide the hotel type")
	@NotEmpty(message = "Please provide the hotel type")
	private HotelType hotelType;

	@NotNull(message = "Please provide the hotel description")
	@NotEmpty(message = "Please provide the hotel description")
	private String hotelDescription;

	@NotNull(message = "Please provide the hotel address")
	@NotEmpty(message = "Please provide the hotel address")
	private String address;

	@NotNull(message = "Please provide the rent details")
	@NotEmpty(message = "Please provide the rent details")
	private Double rent;
	private boolean stats;
	@NotNull(message = "Please provide the STATUS")
	@NotEmpty(message = "Please provide the STATUS")
	private HotelStatus status;

	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "destin_id")
	private Destination destination;

}
