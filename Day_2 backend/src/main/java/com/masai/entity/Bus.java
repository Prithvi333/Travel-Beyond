package com.masai.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

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
