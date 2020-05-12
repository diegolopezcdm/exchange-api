package com.dlopez.exchangecalculator.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestCalculatorOperation{

    @NotNull(message = "Please enter amount")
    public Double amount;
    @NotEmpty(message = "Please enter exchangeFrom")
    public String exchangeFrom;
    @NotEmpty(message = "Please enter exchangeTo")
    public String exchangeTo;

    public void setAmount(Double amount){
        this.amount = amount;
    }

    public void setExchangeFrom(String exchangeFrom){
        this.exchangeFrom = exchangeFrom;
    }

    public void setExchangeTo(String exchangeTo){
        this.exchangeTo = exchangeTo;
    }

    public Double getAmount(){
        return this.amount;
    }

    public String getExchangeFrom(){
        return this.exchangeFrom;
    }

    public String getExchangeTo(){
        return this.exchangeTo;
    }

    public String toString(){
        return "amount :"+this.amount+", exchangeFrom :"+this.exchangeFrom+", exchangeTo"+this.exchangeTo;
    }

}