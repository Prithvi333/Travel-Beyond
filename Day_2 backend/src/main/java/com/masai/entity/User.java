package com.masai.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {


    @NotBlank(message = "userId is mandatory")
    private Integer userId;

    @NotBlank(message = "userType is mandatory")

    private UserType userType;


    @NotBlank(message = "password is mandatory")
    @Size(min = 6,max = 10,message = "min 6 and max 10 is only allowed")
    private String password;

}
