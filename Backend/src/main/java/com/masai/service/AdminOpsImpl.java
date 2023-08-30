package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.entity.Admin;
import com.masai.exception.AdminException;
import com.masai.repository.AdminDao;

@Service
public class AdminOpsImpl implements AdminOps{

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
//    Function or method to add admin
    @Override
    public void addAdmin(Admin admin) {

            Optional<Admin> adminOptional = adminDao.findById(admin.getAdminId());
            if(adminOptional.isPresent()) throw new AdminException("Admin already exist with this id");
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminDao.save(admin);
    }

//    Method that helps in  getting the admin based on email id
    @Override
    public Admin getAdminByEmail(String email) {
        Admin admin = adminDao.findByEmail(email).orElseThrow(()-> new AdminException("username and password is not matched with our database"));
        return admin;
    }

//    Updating the admin here which requres to parametere first is admin itself and its id
    @Override
    public void updateAdmin(Admin admin, Integer adId) {

        Admin admin1 = adminDao.findById(adId).orElseThrow(()-> new AdminException("admin with id does not exist"));
        admin1.setAdminName(admin.getAdminName());
        admin1.setEmail(admin.getEmail());
        admin1.setMobile(admin.getMobile());
        admin1.setPassword(admin.getPassword());
        adminDao.save(admin1);
    }
}
