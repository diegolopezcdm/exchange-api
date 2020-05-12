package com.dlopez.exchangecalculator.service;

import com.dlopez.exchangecalculator.dto.RequestCalculatorOperation;
import com.dlopez.exchangecalculator.dto.ResponseCalculatorOperation;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorService {

    public ResponseCalculatorOperation calculateExchange(RequestCalculatorOperation requestCalculator) throws Exception;

}