package com.dlopez.exchangecalculator.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Audit.class)
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private LocalDate datetime;
    private String action;
    private String details;

    public String getUsername(){
        return this.username;
    }

    public LocalDate getDatetime(){
        return this.datetime;
    }

    public String getAction(){
        return this.action;
    }

    public String getDetails(){
        return this.details;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setDatetime(LocalDate datetime){
        this.datetime = datetime;
    }

    public void setAction(String action){
        this.action = action;
    }

    public void setDetails(String details){
        this.details = details;
    }

}