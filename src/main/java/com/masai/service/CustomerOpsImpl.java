package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyCustomerListException;
import com.masai.repository.CustomerDao;
@Service
public class CustomerOpsImpl implements CustomerOps {

	@Autowired
	CustomerDao cd;

	@Override
	public Customer addCustomer(Customer customer) {

		return cd.save(customer);

	}

	@Override
	public Customer updateCustomer(Customer customer) {

		Optional<Customer> cust = cd.findById(customer.getCustomerId());
		if (!cust.isEmpty()) {
			Customer cus = cust.get();

			customer.setCustomerName(cus.getCustomerName());
			customer.setCustomerPassword(cus.getCustomerPassword());
			customer.setAddress(cus.getAddress());
			customer.setAadharId(cus.getAadharId());
			customer.setGender(cus.getGender());
			customer.setCountry(cus.getCountry());
			customer.setMobileNo(cus.getMobileNo());
			customer.setEmail(cus.getMobileNo());
			return cd.save(customer);
		}
		throw new CustomerNotFoundException("Customer not found with the given id to update");
	}

	@Override
	public Customer deleteCustomer(Customer customer) {

		Optional<Customer> cust = cd.findById(customer.getCustomerId());
		if (!cust.isEmpty()) {

			cd.delete(cust.get());
			return cust.get();
		}
		throw new CustomerNotFoundException("Customer not found with the given id to delete");
	}

	@Override
	public Customer viewCustomerById(int id) {
		Optional<Customer> cust = cd.findById(id);
		if (!cust.isEmpty())
			return cust.get();
		throw new CustomerNotFoundException("Customer not found with the given id to delete");
	}

	@Override
	public List<Customer> viewAllCustomer() {
		List<Customer> customers = cd.findAll();
		if (!customers.isEmpty())
			return customers;
		throw new EmptyCustomerListException("Customer list is empty");
	}

}