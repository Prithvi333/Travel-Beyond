package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Packages;

public interface PackageDao extends JpaRepository<Packages, Integer> {

}
