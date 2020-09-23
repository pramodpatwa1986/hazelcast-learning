package com.mywork.hazelcast.hazelcast.cluster;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class HazelcastClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastClusterApplication.class, args);
	}
	
	@Bean(name = "member1", destroyMethod="shutdown")
	public HazelcastInstance createMember1() {
		return Hazelcast.newHazelcastInstance();
	}

}
