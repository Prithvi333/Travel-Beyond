//package com.masai.security;
//
//import com.masai.entity.Admin;
//import com.masai.entity.Customer;
//import com.masai.repository.AdminDao;
//import com.masai.repository.CustomerDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AdminUserService implements UserDetailsService {
//
//    @Autowired
//    private AdminDao adminDao;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        Optional<Admin> adminOptional = adminDao.findByEmail(username);
//        if(adminOptional.isPresent()){
//            Admin admin = adminOptional.get();
//
//            List<GrantedAuthority> authorities = new ArrayList<>();
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//            authorities.add(simpleGrantedAuthority);
//            return new User(admin.getEmail(),admin.getPassword(),authorities);
//        }else{
//            throw new BadCredentialsException("Admin details not found");
//        }
//
//    }
//}
