package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Admin;
import com.masai.entity.Customer;
import com.masai.service.AdminOps;
import com.masai.service.CustomerOps;

import jakarta.validation.Valid;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/travel")
public class EntryController {

    @Autowired
    private CustomerOps customerService;

    @Autowired
    private AdminOps adminService;
    @GetMapping("/cusLogin")
	public ResponseEntity<String> loginCustomerHandler(Authentication auth){
        System.out.println(auth); // principal object

        Customer customer = customerService.getCustomerByEmail(auth.getName());

        return new  ResponseEntity<>("log in successfully with name "+customer.getCustomerName(), HttpStatus.OK);

    }

//    @GetMapping("/adminLogin")
//    public ResponseEntity<String> adminLoginHandler(Authentication auth){
//        System.out.println(auth); // principal object
//
//        Admin admin = adminService.getAdminByEmail(auth.getName());
//
//        return new  ResponseEntity<>("log in successfully with name "+admin.getAdminName(), HttpStatus.OK);
//
//    }

    @PutMapping("/updateAdmin/{adminId}")
    public ResponseEntity<String> adminUpdateHandler(@RequestBody  @Valid Admin admin, @PathVariable Integer adminId){

        adminService.updateAdmin(admin,adminId);
        return new  ResponseEntity<>("updated successfully "+admin.getAdminName(), HttpStatus.OK);

    }

    @PostMapping("/addAdmin")
    public ResponseEntity<String> adminAddHandler(@RequestBody  @Valid Admin admin){

        adminService.addAdmin(admin);
        return new  ResponseEntity<>(admin.getAdminName()+"added successfully ", HttpStatus.CREATED);

    }
}
