package com.stackroute.searchupstreamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SearchUpstreamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchUpstreamServiceApplication.class, args);
	}

}
