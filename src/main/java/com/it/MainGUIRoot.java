package com.it;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MainGUIRoot {
	public static void main(String[] args) {
		SpringApplication.run(MainGUIRoot.class, args);
	}
	
	//RestTemplate instance is manage by spring container
	@Bean
	@LoadBalanced
	public RestTemplate createBean() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}
	 
}
