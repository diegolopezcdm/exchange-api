package com.dlopez.exchangecalculator.util;

import java.util.HashMap;
import java.util.Map;

import com.dlopez.exchangecalculator.dto.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CurrencyIntegrator {

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
    
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getRemoteCurrencyDefault")
    public Currency getRemoteCurrency(String currencyId){
        
        Currency currency = restTemplate.getForObject("http://CURRENCY-API:8080/currency-api/currencies/"+currencyId, Currency.class);
        return currency;
    }

    public Currency getRemoteCurrencyDefault(String currencyId){
        //TODO: use a cache or second source of currencies.
        //but for testing purposes, I am going to return a dummy objtect

        if(currencyId.equals("3")){
            Currency currency = new Currency();
            currency.setId("3");
            currency.setName("PEN");
            currency.setDescription("PEN");
            Map<String,String> rates = new HashMap();
            rates.put("USD", "3.10");
            currency.setRate(rates);
            return currency; 
        }else if(currencyId.equals("1")){
            Currency currency = new Currency();
            currency.setId("1");
            currency.setName("USD");
            currency.setDescription("USD");
            Map<String,String> rates = new HashMap();
            rates.put("PEN", "3.10");
            currency.setRate(rates);
            return currency; 
        }


        return null;
    }

    //TODO:move a util class
    private ObjectReader currencyReader = null;
    private ObjectReader getCurrencyReader() {

        if (currencyReader != null) return currencyReader;

        ObjectMapper mapper = new ObjectMapper();
        return currencyReader = mapper.reader(Currency.class);
    }

}