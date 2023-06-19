package com.masai.security;


import com.masai.entity.Customer;
import com.masai.entity.UserType;
import com.masai.repository.CustomerDao;
import com.masai.service.CustomerOps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerUserService implements UserDetailsService {


    @Autowired
    private CustomerDao cusRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Customer> customerOptional = cusRepository.findByEmail(username);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();

            List<GrantedAuthority> authorities = new ArrayList<>();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(customer.getRole());
            authorities.add(simpleGrantedAuthority);
            return new User(customer.getEmail(),customer.getCustomerPassword(),authorities);
        }else{
            throw new BadCredentialsException("Customer details not found");
        }

    }
}
