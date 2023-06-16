package com.masai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ticketId;

    @NotNull(message = "status is mandatory")
    @NotBlank(message = "status is mandatory")
    private TicketStatus status;

    @NotNull(message = "passengerId is mandatory")
    @NotBlank(message = "passengerId is mandatory")
    private String passengerId;

    @NotNull(message = "departureLocation is mandatory")
    @NotBlank(message = "departureLocation is mandatory")
    private String departureLocation;

    @NotNull(message = "destinationLocation is mandatory")
    @NotBlank(message = "destinationLocation is mandatory")
    private String destinationLocation;

    @NotNull(message = "seatNumber is mandatory")
    @NotBlank(message = "seatNumber is mandatory")
    private Integer seatNumber;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Packages aPackage;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Route> routes;




}
