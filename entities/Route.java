package com.masaiproject.tripManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.lang.model.element.NestingKind;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer routeId;

    @NotBlank(message = "Please provide the starting details")
    @NotNull(message = "Please provide the starting details")
    private String routeFrom;


    @NotBlank(message = "Please provide the ending details")
    @NotNull(message = "Please provide the ending details")
    private String routeTo;

    @NotBlank(message = "Please provide the departureTime")
    @NotNull(message = "Please provide the departureTime")

    private LocalDateTime departureTime;

    @NotBlank(message = "Please provide the arrivalTime")
    @NotNull(message = "Please provide the arrivalTime")
    private LocalDateTime arrivalTime;

    @NotBlank(message = "Please provide the doj")
    @NotNull(message = "Please provide the doj")
    private LocalDate doj;

    @NotBlank(message = "Please provide the pickupPoint")
    @NotNull(message = "Please provide the pickupPoint")
    private String pickupPoint;

    @NotBlank(message = "Please provide the fare price")
    private double fare;

}
