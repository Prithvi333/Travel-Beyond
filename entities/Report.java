package com.masaiproject.tripManagement.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer reportId;
    @NotNull(message = "please provide the reason")
    @NotBlank(message = "reportName can't be empty")
    private String reportName;

    @NotNull(message = "select type")
    private ReportType reportType;

    @NotNull(message = "please provide customer ID")
    @NotEmpty(message = "please provide customer ID")
    private String customerId;

}
