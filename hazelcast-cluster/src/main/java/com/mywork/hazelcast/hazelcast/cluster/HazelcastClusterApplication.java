package com.mywork.hazelcast.hazelcast.cluster;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MapStoreConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class HazelcastClusterApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastClusterApplication.class, args);
	}

	@Bean(name = "member1", destroyMethod = "shutdown")
	public HazelcastInstance createMember1(
			@Qualifier("StoreNodeConfig") 
			Config config) throws Exception{
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean(name = "StoreNodeConfig")
	public Config config(CustomerMapStore customerMapStore) throws Exception {

		System.out.println("Config started");
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
