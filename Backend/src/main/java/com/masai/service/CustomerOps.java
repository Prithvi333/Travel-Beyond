package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;

public interface CustomerOps {

	Customer addCustomer(Customer customer);

	Customer updateCustomer(Integer cid, Customer customer);

	Customer deleteCustomer(Integer cid);

	Customer viewCustomerById(int id);

	List<Customer> viewAllCustomer();


	Customer getCustomerByEmail(String email);

}
