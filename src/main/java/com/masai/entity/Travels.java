package com.masai.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Travels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer travelsId;

    @NotNull(message = "travelsName is mandatory")
    @NotBlank(message = "travelsName is mandatory")
    private String travelsName;

    @NotNull(message = "agentName is mandatory")
    @NotBlank(message = "agentName is mandatory")
    private String agentName;
   
    private boolean status;
    
    @NotNull(message = "address is mandatory")
    @NotBlank(message = "address is mandatory")
    private String address;

    @NotNull(message = "contact is mandatory")
    @NotBlank(message = "contact is mandatory")
    private String contact;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "travel")
    private List<Bus> buses;


}
