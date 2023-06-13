package com.masaiproject.tripManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packageId;

    @NotBlank(message = "Please provide the package name")
    @NotNull(message = "Please provide the customer name")
    @Size(min = 4,message = "min 4 is required")
    private String packageName;

    @NotBlank(message = "Please provide the hotel type")
    @NotNull(message = "Please provide the hotel type")
    private HotelType hotelType;

    @NotBlank(message = "Please provide the daysAndNight details")
    @NotNull(message = "Please provide the daysAndNight details")
    private String daysAndNight;


    @NotBlank(message = "Please provide the package season")
    @NotNull(message = "Please provide the package season")
    private Season packageSeason;

    @NotBlank(message = "At least 3 package description is required")
    @NotNull(message = "At least 3 package description is required")
    private String packageDescription1;

    @NotBlank(message = "At least 3 package description is required")
    @NotNull(message = "At least 3 package description is required")
    private String packageDescription2;

    @NotBlank(message = "At least 3 package description is required")
    @NotNull(message = "At least 3 package description is required")
    private String packageDescription3;

    private String packageDescription4;
    private String packageDescription5;

    @NotBlank(message = "please provide package cost")
    private double packageCost;


    @Embedded
    private PaymentDetails payment;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Hotel> hotels;

    @OneToOne(mappedBy = "aPackage")
    private Booking booking;



}
