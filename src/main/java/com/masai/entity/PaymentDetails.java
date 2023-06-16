package com.masai.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @NotNull(message = "type can not be null")
    private PaymentType paymentType;

    private PaymentStatus paymentStatus;

//    @Column(name = "payment_money", columnDefinition = "DECIMAL(10, 2) DEFAULT 0.0")

    @NotNull(message = "money can not be null")
    private BigDecimal paymentMoney ;

    private Integer bookingId;


}
