package com.masaiproject.tripManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @NotNull(message = "booking type is important")
    private String bookingType;


    private String description;

    @NotNull(message = "give any booking title")
    private String bookingTitle;

    @CreatedDate
    private LocalDate bookingDate;

    @NotNull(message = "mention the number of person that will be in this trip")
    @NotBlank(message = "mention the number of person that will be in this trip")
    private Integer number_Of_Person;






}
