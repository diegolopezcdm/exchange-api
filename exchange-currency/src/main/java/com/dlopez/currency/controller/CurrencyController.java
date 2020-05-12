package com.dlopez.currency.controller;

import java.util.List;

import com.dlopez.currency.model.Money;
import com.dlopez.currency.service.CurrencyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("")
	public List<Money> getCurrencies() {
		return currencyService.getCurrencies();
    }
    
    @GetMapping("/{currencyId}")
	public Money getCurrency(@PathVariable String currencyId) throws Exception{
		return currencyService.getCurrency(currencyId);
    }

}