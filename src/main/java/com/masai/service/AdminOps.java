package com.masai.service;

import com.masai.entity.Admin;

public interface AdminOps {

    void addAdmin(Admin admin);

    Admin getAdminByEmail(String email);
    void updateAdmin(Admin admin,Integer adId);
}
