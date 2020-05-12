package com.dlopez.exchangecalculator.service;

import java.time.LocalDate;

import com.dlopez.exchangecalculator.entity.Audit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService{
 
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.audit-queue}")
    public String queueName;

    public void save(String username, String method, Object detail) throws Exception{
        Audit audit = new Audit();
        audit.setUsername(username);
        audit.setAction(method);
        audit.setDatetime(LocalDate.now());
        audit.setDetails(detail.toString());
        rabbitTemplate.convertAndSend(queueName, audit);
    }

}