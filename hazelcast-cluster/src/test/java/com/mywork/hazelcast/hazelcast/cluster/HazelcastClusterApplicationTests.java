package com.mywork.hazelcast.hazelcast.cluster;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

@SpringBootTest
class HazelcastClusterApplicationTests {
	
	@Autowired
	@Qualifier("member1")
	 HazelcastInstance hazelcastInstance;
	
	 @TestConfiguration
	     class EmployeeServiceImplTestContextConfiguration {
	 
	        @Bean
	        public CustomerService employeeService() {
	            return new CustomerService(hazelcastInstance);
	        }
	    }
	
	@Autowired
	CustomerService customerService;
	
	
	@BeforeEach
	public void tearDown() {
		hazelcastInstance.getMap("CUSTOMER").clear();
	}
	
	@Test
	void testAddCustomer() {
		Customer customer = new Customer(1L, "Bob", new Date(), "bob.work@gmail.com");
		customerService.addCustomer(customer);
		IMap<Long, Customer> customerMap = hazelcastInstance.getMap("CUSTOMER");
		assertEquals(1, customerMap.size());
	}
	
	@Test
	void testAddAllCustomer() {
		Collection<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1L, "Bob", new Date(), "bob.work@gmail.com"));
		customerList.add(new Customer(2L, "Tom", new Date(), "tom.work@gmail.com"));		
		customerService.addCustomers(customerList);
		IMap<Long, Customer> customerMap = hazelcastInstance.getMap("CUSTOMER");
		assertEquals(2, customerMap.size());
	}
	
	@Test
	void testUpdateCustomer() {
		Customer customer = new Customer(1L, "Bob", new Date(), "bob.work@gmail.com");
		customerService.addCustomer(customer);
		
		Customer customerToUpdate = (Customer)hazelcastInstance.getMap("CUSTOMER").get(1L);
		customerToUpdate.setName("BobX");
		customerService.updateCustomer(customerToUpdate);
		
		IMap<Long, Customer> customerMap = hazelcastInstance.getMap("CUSTOMER");
		assertEquals("BobX", ((Customer)customerMap.get(1L)).getName());
	}
	
	@Test
	void testDeleteCustomer() {
		Collection<Customer> customerList = new ArrayList<Customer>();
		customerList.add(new Customer(1L, "Bob", new Date(), "bob.work@gmail.com"));
		Customer customertoDelete = new Customer(2L, "Tom", new Date(), "tom.work@gmail.com");
		customerList.add(customertoDelete);		
		customerService.addCustomers(customerList);
		customerService.deleteCustomer(customertoDelete);
		IMap<Long, Customer> customerMap = hazelcastInstance.getMap("CUSTOMER");
		assertEquals(1, customerMap.size());
	}

}
