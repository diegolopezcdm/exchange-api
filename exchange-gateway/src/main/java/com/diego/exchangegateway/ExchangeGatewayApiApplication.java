package com.diego.exchangegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class ExchangeGatewayApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangeGatewayApiApplication.class, args);
	}

}
