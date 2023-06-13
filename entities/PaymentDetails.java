package com.masaiproject.tripManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
public class PaymentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;


    @NotNull(message = "payment type is required")
    @NotNull(message = "payment type is required")
    private PaymentType paymentType;

    @NotNull(message = "payment money is required")
    @NotNull(message = "payment money is required")
    private double paymentMoney;


}
