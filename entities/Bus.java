package com.masaiproject.tripManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer busId;

    @NotBlank(message = "Please provide the destination name")
    @NotNull(message = "Please provide the destination name")
    private String busType;

    @NotBlank(message = "Please provide the destination name")
    @NotNull(message = "Please provide the destination name")
    private String busNumber;

    @NotBlank(message = "Please provide the destination name")
    @NotNull(message = "Please provide the destination name")
    private Integer capacity;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Route> routes;


    @JoinColumn(name = "travels_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Travels travel;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "bus_destination",joinColumns = {@JoinColumn(referencedColumnName = "busId")},inverseJoinColumns = {@JoinColumn(referencedColumnName = "desId")})
    private List<Destination> destinationList;


}
