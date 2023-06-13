package com.masaiproject.tripManagement.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

public class Destination {

    private Integer desId;
    private String name;
    private DesEnvironment desEnvironment;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "destinationList")

    private List<Bus> bus;

    @OneToMany(mappedBy = "destination",cascade = CascadeType.ALL)
    private List<Hotel> hotels;

}
