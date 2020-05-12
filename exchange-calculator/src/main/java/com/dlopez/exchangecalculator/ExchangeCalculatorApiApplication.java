package com.dlopez.exchangecalculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class ExchangeCalculatorApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(ExchangeCalculatorApiApplication.class, args);
	}

}
