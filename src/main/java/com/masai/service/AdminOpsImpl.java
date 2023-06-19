package com.masai.service;

import com.masai.entity.Admin;
import com.masai.entity.Customer;
import com.masai.exception.AdminException;
import com.masai.exception.CustomerNotFoundException;
import com.masai.repository.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminOpsImpl implements AdminOps{

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void addAdmin(Admin admin) {

            Optional<Admin> adminOptional = adminDao.findById(admin.getAdminId());
            if(adminOptional.isPresent()) throw new AdminException("Admin already exist with this id");
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminDao.save(admin);
    }

    @Override
    public Admin getAdminByEmail(String email) {
        Admin admin = adminDao.findByEmail(email).orElseThrow(()-> new AdminException("username and password is not matched with our database"));
        return admin;
    }

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
