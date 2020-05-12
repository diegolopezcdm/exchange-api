package com.dlopez.exchangecalculator.dto;

import java.util.HashMap;
import java.util.Map;

public class Currency {

    private String id;
    private String name;
    private String description;
    private Map<String,String> rate = new HashMap();

    public String getId(){
        return id;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Map<String,String> getRate(){
        return this.rate;
    }

    public void setId(String id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setRate(Map<String,String> rate){
        this.rate = rate;
    }

}