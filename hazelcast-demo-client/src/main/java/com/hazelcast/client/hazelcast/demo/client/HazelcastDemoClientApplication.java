package com.hazelcast.client.hazelcast.demo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@SpringBootApplication
@Configuration
public class HazelcastDemoClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(HazelcastDemoClientApplication.class, args);
	}
	
	@Bean
	public ClientConfig clientConfig() {
		ClientConfig clientConfig =  new ClientConfig();
		ClientNetworkConfig clientNetworkConfig = clientConfig.getNetworkConfig();
		clientNetworkConfig.setConnectionTimeout(0);
		return clientConfig;
	}
	@Bean(destroyMethod = "shutdown")
	public HazelcastInstance createStorageClient(ClientConfig clientconfig) throws Exception {
		return HazelcastClient.newHazelcastClient(clientconfig);
	}

}
