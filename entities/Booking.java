//package com.masaiproject.tripManagement.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonSetter;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//
//import java.time.LocalDate;
//import java.util.List;
//
//
//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
//@NoArgsConstructor
//@AllArgsConstructor
//@Data
//public class Booking {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer bookingId;
//
//    @NotNull(message = "booking type is important")
//    private String bookingType;
//
//
//    private String description;
//
//    @NotNull(message = "give any booking title")
//    private String bookingTitle;
//
//    @CreatedDate
//    private LocalDate bookingDate;
//
//
//    @NotNull(message = "choose the package")
//    @NotBlank(message = "choose the package")
//    private String packageName;
//
//    @NotNull(message = "mention the number of person that will be in this trip")
//    private Integer number_Of_Person;
//
//
////    @OneToOne(cascade = CascadeType.ALL)
////    private Package aPackage;
//

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.OneToMany;

//@OneToMany(cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<PaymentDetails> payment;
////
////    @JoinColumn(name = "customer_id")
////    @ManyToOne(cascade = CascadeType.ALL)
////    @JsonIgnore
////    private Customer customerBooking;
//
//
//
//}
