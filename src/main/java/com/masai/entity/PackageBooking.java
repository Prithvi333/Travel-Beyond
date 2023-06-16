package com.masai.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageBooking{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

    @NotNull(message = "booking type is important")
    private String bookingType;


    private String description;
    private boolean status;
    @NotNull(message = "give any booking title")
    private String bookingTitle;

    @CreatedDate
    private LocalDate bookingDate;


    @NotNull(message = "choose the package")
    @NotBlank(message = "choose the package")
    private String packageName;

    @NotNull(message = "mention the number of person that will be in this trip")
    private Integer number_Of_Person;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<PaymentDetails> payment=new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "package_id")
    private Package aPackage;

}
