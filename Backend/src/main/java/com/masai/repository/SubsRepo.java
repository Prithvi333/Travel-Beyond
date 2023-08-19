package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.SubscribedEmail;

public interface SubsRepo extends JpaRepository< SubscribedEmail,Integer> {

}
