package com.mywork.hazelcast.hazelcast.cluster.member2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mywork.hazelcast.hazelcast.cluster.Customer;

@RestController
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public void addCustomer(@RequestBody Customer customer) {
		System.out.println("Id is: "+customer.getId());
		customerService.addCustomer(customer);
	}
	
	@GetMapping("/customers") 
	
	public  List<Customer> getCustomers(){		
		return customerService.getCustomers();
	}
	
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable Long id){		
		return customerService.getCustomer(id);
	}

}
