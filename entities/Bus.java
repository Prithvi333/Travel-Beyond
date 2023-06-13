package com.masaiproject.tripManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class Bus {

    private Integer busId;
    private String busType;

    private String busNumber;
    private Integer capacity;

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
