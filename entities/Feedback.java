package com.masaiproject.tripManagement.entities;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class Feedback {

    private Integer feedbackId;
    private String feedback;
    private String rating;

    private LocalDate submitDate;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
