package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.masai.entity.Customer;
import com.masai.entity.Report;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyReportListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.exception.ReportNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.ReportDao;
import org.springframework.stereotype.Service;

@Service
public class ReportOpsImpl implements ReportOps {

	@Autowired
	CustomerDao cd;
	@Autowired
	ReportDao rd;

	@Override
	public Report addReport(int customerId, Report report) {

		Optional<Customer> customer = cd.findById(customerId);
		if (!customer.isEmpty()) {
			if (!customer.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Customer is invalid now to report");
			}
			report.setCustomerId(customerId + "");
			report.setStatus(true);
			return rd.save(report);
		}
		throw new CustomerNotFoundException("Customer not found to report");
	}

	@Override
	public Report deleteReport(int reportId) {

		Optional<Report> report = rd.findById(reportId);
		if (!report.isEmpty()) {
			if (!report.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to delete already deleted report");
			}
			report.get().setStatus(false);
			return rd.save(report.get());
		}
		throw new ReportNotFoundException("Report not found to delete");
	}

	@Override
	public Report viewReport(int reportId) {
		Optional<Report> report = rd.findById(reportId);
		if (!report.isEmpty()) {
			if (!report.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Report is not available now");
			}
			return report.get();
		}
		throw new ReportNotFoundException("Report not found to view");
	}

	@Override
	public List<Report> viewAllReport() {
		List<Report> reportList = rd.findAll();
		if (!reportList.isEmpty())
			return reportList.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyReportListException("No report found");
	}

}
