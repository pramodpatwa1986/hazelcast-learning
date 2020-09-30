package com.mywork.hazelcast.hazelcast.cluster.member2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.mywork.hazelcast.hazelcast.cluster.Customer;

@Service
public class CustomerService{
	
	private HazelcastInstance  hazelcastInstance;
	private IMap<Long, Customer> customerMap;
	
	public CustomerService (@Qualifier("member2") HazelcastInstance hazelcastInstance ) {
		this.hazelcastInstance = hazelcastInstance;
	}
	
	@PostConstruct
	public void init() {
		customerMap = hazelcastInstance.getMap("CUSTOMER");
	}
	
	public void addCustomer (Customer customer) {
		customerMap.put(customer.getId(), customer);
	}
	
	public void addCustomers (Collection<Customer> customers) {
		
		Map<Long, Customer> localMap = new HashMap<Long, Customer>();
		for(Customer customer: customers) {
			localMap.put(customer.getId(), customer);
		}
		customerMap.putAll(localMap);
	}
	
	public void updateCustomer (Customer customer) {
		customerMap.put(customer.getId(), customer);
	}
	
	public void deleteCustomer (Customer customer) {
		customerMap.delete(customer.getId());
	}
	
	public List<Customer> getCustomers(){		
		return new ArrayList<Customer>(customerMap.values());
	}
	
	public Customer getCustomer(Long id){		
		return customerMap.get(id);
	}
}
