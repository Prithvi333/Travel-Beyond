package com.masai.entity;

import java.time.LocalDate;

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

	private LocalDate localDate;

	private PaymentStatus paymentStatus;

//    @Column(name = "payment_money", columnDefinition = "DECIMAL(10, 2) DEFAULT 0.0")

	private boolean status;
	private String paymentMoney;

	private Integer bookingId;

	private Integer customerId;

}
