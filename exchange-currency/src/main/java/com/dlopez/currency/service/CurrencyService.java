package com.dlopez.currency.service;

import com.dlopez.currency.model.Money;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CurrencyService {

    public List<Money> getCurrencies();
    public Money getCurrency(String currencyId) throws Exception;

}