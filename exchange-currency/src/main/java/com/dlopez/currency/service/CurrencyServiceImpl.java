package com.dlopez.currency.service;

import com.dlopez.currency.model.Money;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService{

    @Value("#{'${currencies}'.split(';')}")
    List<String> currenciesConfig;
    List<Money> currencyList = new ArrayList<>();

    @Override
    public List<Money> getCurrencies(){

        if(currencyList.isEmpty()){
            currenciesConfig.forEach( line->{
                String[] values = line.split(",");
                Money currency = new Money();
                currency.setId(values[0]);
                currency.setName(values[1]);
                currency.setDescription(values[2]);
    
                String[] rateLine = values[3].split("-");
    
                for (String string : rateLine) {
                    String money = string.split(":")[0];
                    String moneyRate = string.split(":")[1];
                    currency.getRate().put(money, moneyRate);
                }
    
                currencyList.add(currency);
                
            });
        }

        return currencyList;
    }

    @Override
    public Money getCurrency(String currencyId) throws Exception {

        if(currencyList.isEmpty())
            getCurrencies();

        Optional<Money> currency = currencyList.stream().
                                                    filter(var -> var.getId().equals(currencyId)).
                                                    findAny();

        if(currency.isPresent())
            return currency.get();
        
        throw new Exception("currencyId not found");

    }


}