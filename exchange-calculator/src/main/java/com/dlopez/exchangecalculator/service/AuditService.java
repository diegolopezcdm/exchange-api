package com.dlopez.exchangecalculator.service;

import org.springframework.stereotype.Service;

@Service
public interface AuditService {

    public void save(String username, String method, Object detail) throws Exception;

}