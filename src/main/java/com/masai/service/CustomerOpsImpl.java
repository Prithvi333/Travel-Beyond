package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyCustomerListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.repository.CustomerDao;

@Service
public class CustomerOpsImpl implements CustomerOps {

	@Autowired
	CustomerDao cd;

	@Override
	public Customer addCustomer(Customer customer) {
		customer.setStatus(true);
		return cd.save(customer);

	}

	@Override
	public Customer updateCustomer(Customer customer) {

		Optional<Customer> cust = cd.findById(customer.getCustomerId());
		if (cust.isPresent()) {
			Customer cus = cust.get();
			if (!cus.isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to update already deleted customer");
			}
//			customer.setCustomerName(cus.getCustomerName());
//			customer.setCustomerPassword(cus.getCustomerPassword());
//			customer.setAddress(cus.getAddress());
//			customer.setAadharId(cus.getAadharId());
//			customer.setGender(cus.getGender());
//			customer.setCountry(cus.getCountry());
//			customer.setMobileNo(cus.getMobileNo());
//			customer.setEmail(cus.getEmail());
//			return cd.save(customer);

			cus.setCustomerName(customer.getCustomerName());
			cus.setCustomerPassword(customer.getCustomerPassword());
			cus.setAddress(customer.getAddress());
			cus.setAadharId(customer.getAadharId());
			cus.setGender(customer.getGender());
			cus.setCountry(customer.getCountry());
			cus.setMobileNo(customer.getMobileNo());
			cus.setEmail(customer.getEmail());
			return cd.save(cus);
		}
		throw new CustomerNotFoundException("Customer not found with the given id to update");
	}

	@Override
	public Customer deleteCustomer(Customer customer) {

		Optional<Customer> cust = cd.findById(customer.getCustomerId());
		if (!cust.isEmpty()) {
			if (!cust.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to remove already deleted customer");
			}
			cd.delete(cust.get());
			return cust.get();
		}
		throw new CustomerNotFoundException("Customer not found with the given id to delete");
	}

	@Override
	public Customer viewCustomerById(int id) {
		Optional<Customer> cust = cd.findById(id);
		if (!cust.isEmpty()) {
			if (!cust.get().isStatus()) {
				throw new EntityAlreadyAlteredException("This customer is not exist now");
			}
			return cust.get();
		}
		throw new CustomerNotFoundException("Customer not found with the given id to delete");
	}

	@Override
	public List<Customer> viewAllCustomer() {
		List<Customer> customers = cd.findAll();
		if (!customers.isEmpty())
			return customers.stream().filter(a -> a.isStatus()).toList();
		throw new EmptyCustomerListException("Customer list is empty");
	}

}
