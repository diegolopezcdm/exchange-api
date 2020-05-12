package com.diego.exxhangesecurity.dto;

public class UserPostRequest {

    private String username;
    private String password;

    public void setUsername(String userName){
        this.username=userName;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

}