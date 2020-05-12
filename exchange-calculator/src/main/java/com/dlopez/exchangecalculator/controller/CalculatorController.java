package com.dlopez.exchangecalculator.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import com.diego.exxhangesecurity.entity.User;
import com.dlopez.exchangecalculator.dto.RequestCalculatorOperation;
import com.dlopez.exchangecalculator.dto.ResponseCalculatorOperation;
import com.dlopez.exchangecalculator.entity.Audit;
import com.dlopez.exchangecalculator.service.AuditService;
import com.dlopez.exchangecalculator.service.CalculatorService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @Autowired
    AuditService auditService;

    @PostMapping("")
	public ResponseCalculatorOperation calculateExchange(@Valid @RequestBody RequestCalculatorOperation request, OAuth2Authentication authentication) throws Exception{
        User principal = (User) authentication.getUserAuthentication().getPrincipal();
        auditService.save(principal.getUsername(), "post calculateExchange", request);
        return calculatorService.calculateExchange(request);
    }

}