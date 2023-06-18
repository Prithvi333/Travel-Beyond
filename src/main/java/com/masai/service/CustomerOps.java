package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.entity.CustomerDto;

public interface CustomerOps {

	Customer addCustomer(Customer customer);

	Customer updateCustomer(int cusId, CustomerDto customer);

	Customer deleteCustomer(Customer customer);

	Customer viewCustomerById(int id);

	List<Customer> viewAllCustomer();

}
