package com.mywork.hazelcast.hazelcast.cluster.member2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@EntityScan(basePackages = {"com.mywork.hazelcast.hazelcast.cluster"})
@Configuration
public class HazelcastClusterMember2Application {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastClusterMember2Application.class, args);
	}
	
	@Bean(name = "member2",destroyMethod="shutdown")
	public HazelcastInstance createMember2(
			@Qualifier("StoreNodeConfig") 
			Config config) {
		return Hazelcast.newHazelcastInstance(config);
	}
	
	@Bean(name = "StoreNodeConfig")
	public Config config(CustomerMapStore customerMapStore) throws Exception {

		System.out.println("Config started Member 2");
		Config config = new Config();

		MapConfig customerMapConfig = new MapConfig();
		
		MapStoreConfig customerMapStoreConfig = new MapStoreConfig();
		
		customerMapStoreConfig.setImplementation(customerMapStore);
		
		customerMapConfig.setMapStoreConfig(customerMapStoreConfig);
		customerMapConfig.setName("CUSTOMER");
		
		config.addMapConfig(customerMapConfig);

		return config;
	}

}
