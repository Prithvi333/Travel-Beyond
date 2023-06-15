package com.masai.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer feedbackId;

    @NotBlank(message = "Please provide the feedback details")
    @NotNull(message = "Please provide the feedback details")
    private String feedback;

    @NotBlank(message = "Please provide the rating as well")
    @NotNull(message = "Please provide the rating as well")
    private Integer rating;

    @CreatedDate
    private LocalDate submitDate;

    @ManyToOne()
    @JoinColumn(name = "customer_id")
    private Customer customerFeedback;
}
