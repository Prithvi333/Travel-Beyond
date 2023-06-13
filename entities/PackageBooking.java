package com.masaiproject.tripManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn (name = "package_booking_id")
public class PackageBooking extends Booking{

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "booking")
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    private Package aPackage;

}
