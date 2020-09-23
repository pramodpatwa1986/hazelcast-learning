package com.mywork.hazelcast.hazelcast.cluster;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
