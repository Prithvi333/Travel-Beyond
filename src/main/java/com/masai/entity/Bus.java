package com.masai.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
