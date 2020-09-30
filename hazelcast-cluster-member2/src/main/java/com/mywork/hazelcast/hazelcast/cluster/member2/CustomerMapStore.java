package com.mywork.hazelcast.hazelcast.cluster.member2;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.map.MapStore;
import com.mywork.hazelcast.hazelcast.cluster.Customer;

@Service
public class CustomerMapStore implements MapStore<Long, Customer> {

	CustomerRepository customerRepository;

	@Autowired
	public CustomerMapStore(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer load(Long key) {
		// TODO Auto-generated method stub
		return customerRepository.findById(key).orElse(new Customer());
	}

	@Override
	public Map<Long, Customer> loadAll(Collection<Long> keys) {
		// TODO Auto-generated method stub
		Iterable<Customer> customers = customerRepository.findAllById(keys);
		
		return StreamSupport.stream(customers.spliterator(), false)				
				.collect(Collectors.toMap(Customer::getId, Function.identity()));
	}

	@Override
	public Iterable<Long> loadAllKeys() {
		Iterable<Customer> customers = customerRepository.findAll();
		
		return StreamSupport.stream(customers.spliterator(), false)
				.map(Customer::getId)
				.collect(Collectors.toList());
	}

	@Override
	public void store(Long key, Customer value) {
		// TODO Auto-generated method stub
		System.out.println("Going to store");
		customerRepository.save(value);

	}

	@Override
	public void storeAll(Map<Long, Customer> map) {
		customerRepository.saveAll(map.values());

	}

	@Override
	public void delete(Long key) {
		// TODO Auto-generated method stub		
		customerRepository.delete(load(key));

	}

	@Override
	public void deleteAll(Collection<Long> keys) {
		// TODO Auto-generated method stub
		Iterable<Customer> customers = customerRepository.findAllById(keys);
		customerRepository.deleteAll(customers);

	}

}
