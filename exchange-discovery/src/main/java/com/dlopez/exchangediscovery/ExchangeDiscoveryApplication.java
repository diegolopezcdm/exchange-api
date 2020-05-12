package com.dlopez.exchangediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ExchangeDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeDiscoveryApplication.class, args);
	}

}
