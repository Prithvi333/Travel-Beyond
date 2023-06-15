package com.masaiproject.tripManagement.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer desId;

    @NotBlank(message = "Please provide the destination name")
    @NotNull(message = "Please provide the destination name")
    private String name;

    @NotNull(message = "Please choose desEnvironment")
    private DesEnvironment desEnvironment;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "destinationList")
    @JsonIgnore
    private List<Bus> bus;

    @JsonIgnore
    @OneToMany(mappedBy = "destination",cascade = CascadeType.ALL)
    private List<Hotel> hotels;

}
