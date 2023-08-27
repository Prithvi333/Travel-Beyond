package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.SubscribedEmail;
import com.masai.exception.CustomerNotFoundException;
import com.masai.exception.EmptyCustomerListException;
import com.masai.exception.EntityAlreadyAlteredException;
import com.masai.repository.CustomerDao;
import com.masai.repository.SubsRepo;

@Service
public class CustomerOpsImpl implements CustomerOps {

	@Autowired
	CustomerDao cd;
	@Autowired
	private SubsRepo sr;
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	@Override
	public Customer addCustomer(Customer customer) {
		customer.setStatus(true);

		customer.setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
		if(customer.getRole()==null){
			customer.setRole("ROLE_USER");
		}
		else {
			customer.setRole("ROLE_USER");
		}			
		List<Customer> list= viewAllCustomer();
		
		list.forEach(data->{
			if(data.getEmail().equals((customer.getEmail())))
				throw new EntityAlreadyAlteredException("Customer already registered");
		});
		
		return cd.save(customer);
//		 if(customer.getRole().equals("ADMIN")){
//			customer.setRole("ROLE_ADMIN");
//		}
//		 else if(customer.getRole().equals("USER")){
//			customer.setRole("ROLE_USER");
//		}
//		 else if(customer.getRole().equals("ROLE_USER")){
//			 customer.setRole("ROLE_USER");
//		}
//		 else if(customer.getRole().equals("ROLE_ADMIN")){
//			 customer.setRole("ROLE_ADMIN");
//		}
//		 else{
//			customer.setRole("ROLE_USER");
//		}

	}

	@Override
	public Customer updateCustomer(Integer cid,Customer cus) {

		Optional<Customer> cust = cd.findById(cid);
		if (cust.isPresent()) {
			Customer customer = cust.get();
			if (!cus.isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to update already deleted customer");
			}
			
			customer.setCustomerName(cus.getCustomerName());
			customer.setCustomerPassword(cus.getCustomerPassword());
			customer.setAddress(cus.getAddress());
			customer.setAadharId(cus.getAadharId());
			customer.setGender(cus.getGender());
			customer.setCountry(cus.getCountry());
			customer.setMobileNo(cus.getMobileNo());
			customer.setEmail(cus.getEmail());
			return cd.save(customer);

//			cus.setCustomerName(customer.getCustomerName());
//			cus.setCustomerPassword(customer.getCustomerPassword());
//			cus.setAddress(customer.getAddress());
//			cus.setAadharId(customer.getAadharId());
//			cus.setGender(customer.getGender());
//			cus.setCountry(customer.getCountry());
//			cus.setMobileNo(customer.getMobileNo());
//			cus.setEmail(customer.getEmail());

//			return cd.save(cus);
		}
		throw new CustomerNotFoundException("Customer not found with the given id to update");
	}

	@Override
	public Customer deleteCustomer(Integer cid) {

		Optional<Customer> cust = cd.findById(cid);
		if (!cust.isEmpty()) {
			if (!cust.get().isStatus()) {
				throw new EntityAlreadyAlteredException("Unable to remove already deleted customer");
			}
			Customer cus=cust.get();
	       cus.setStatus(false);
			return cd.save(cus);
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

	@Override
	public Customer getCustomerByEmail(String email) {
		Customer customer = cd.findByEmail(email).orElseThrow(()-> new CustomerNotFoundException("username and password is not matched with our database"));
		return customer;
	}

	@Override
	public String subsCustomer(SubscribedEmail se) {
		List<SubscribedEmail> list= sr.findAll();
		for(SubscribedEmail value:list) {
			
			if(value.getEmail().equals(se.getEmail()))
				return "Email is already there";
		}
		sr.save(se);
		return "Subscribed!";
	}

}
