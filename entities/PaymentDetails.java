package com.masaiproject.tripManagement.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
public class PaymentDetails {


    private Integer paymentId;

    private PaymentType paymentType;

    private double paymentMoney;


}
