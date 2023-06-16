package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Report;

public interface ReportDao extends JpaRepository<Report, Integer>{

	
}
