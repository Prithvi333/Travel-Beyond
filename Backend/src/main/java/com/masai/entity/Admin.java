package com.masai.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer adminId;

    @NotNull(message = "Name is mandatory")
    @Size(min = 3,max = 30,message = "Min 3 and Max 30 Characters is allowed only")
    private String adminName;

    @Size(min = 6,max = 10,message = "Min 3 and Max 30 Characters is allowed only")
    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password is mandatory")
    private String password;

    @Email(message = "please provide correct email")
    @NotBlank(message = "please provide correct email")
    private String email;

    @Size(min = 10,max = 10,message = "Min 10 and Max 30 Characters is allowed only")
    @NotNull(message = "mobile is mandatory")
    @NotBlank(message = "mobile is mandatory")
    private String mobile;


    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Report> reports;
}
