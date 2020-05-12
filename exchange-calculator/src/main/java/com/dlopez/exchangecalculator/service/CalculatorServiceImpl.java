package com.dlopez.exchangecalculator.service;

import java.util.Optional;
import java.util.Map.Entry;

import com.dlopez.exchangecalculator.configuration.RibbonConfiguration;
import com.dlopez.exchangecalculator.dto.Currency;
import com.dlopez.exchangecalculator.dto.RequestCalculatorOperation;
import com.dlopez.exchangecalculator.dto.ResponseCalculatorOperation;
import com.dlopez.exchangecalculator.util.CurrencyIntegrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;

@Service
@RibbonClient(name = "server", configuration = RibbonConfiguration.class)
public class CalculatorServiceImpl implements CalculatorService{
 
    @Autowired
    CalculatorService calculatorService;

    @Autowired
    CurrencyIntegrator loadBalanceUtil;

    
    public ResponseCalculatorOperation calculateExchange(RequestCalculatorOperation requestCalculator) throws Exception{
        Currency currencyExchangeFrom = loadBalanceUtil.getRemoteCurrency(requestCalculator.getExchangeFrom());

        if(currencyExchangeFrom == null){
            throw new Exception("currencyExchage from not found");
        }

        Currency currencyExchangeTo = loadBalanceUtil.getRemoteCurrency(requestCalculator.getExchangeTo());

        if(currencyExchangeTo == null){
            throw new Exception("currencyExchage to not found");
        }

        Optional<Entry<String, String>> currencyExchange =currencyExchangeFrom.getRate().entrySet().stream().
                                                    filter( entry-> {
                                                        return entry.getKey().contentEquals(currencyExchangeTo.getName());
                                                    }).
                                                        findAny();
                                                
        if(!currencyExchange.isPresent()){
            throw new Exception("money exchange not supported from "+requestCalculator.getExchangeFrom()+
                    " to "+requestCalculator.getExchangeTo());
        } else {
            ResponseCalculatorOperation responseCalculator = new ResponseCalculatorOperation();
            responseCalculator.setAmount(requestCalculator.amount*Double.parseDouble(currencyExchange.get().getValue()));
            return responseCalculator;
        }

    }

}