package com.masaiproject.tripManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "hotel_booking_id")
public class HotelBooking extends Booking{

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "booking")
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL)
    private Hotel hotel;

}
