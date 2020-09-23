package com.mywork.hazelcast.hazelcast.cluster.member2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class HazelcastClusterMember2Application {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastClusterMember2Application.class, args);
	}
	
	@Bean(name = "member2",destroyMethod="shutdown")
	public HazelcastInstance createMember2() {
		return Hazelcast.newHazelcastInstance();
	}

}
