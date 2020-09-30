package com.mywork.hazelcast.hazelcast.cluster.member2;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import com.mywork.hazelcast.hazelcast.cluster.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long> {	
}
