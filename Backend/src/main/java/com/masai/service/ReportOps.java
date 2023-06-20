package com.masai.service;

import java.util.List;

import com.masai.entity.Report;

public interface ReportOps {

	Report addReport(int customerId, Report report);

	Report deleteReport(int reportId);

	Report viewReport(int reportId);

	List<Report> viewAllReport();
}
