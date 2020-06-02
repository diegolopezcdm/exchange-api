#!/bin/bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

cd exchange-config;
note "Building exchange-config..."; 
./mvnw clean package;
note "Building exchange-config DONE"; 
cd ..;
cd exxhange-security;
note "Building exchange-security..."; 
./mvnw clean install -Dmaven.test.skip=true;
note "Building exchange-security DONE"; 
cd ..;
cd exchange-currency;
note "Building exchange-currency..."; 
./mvnw clean package -Dmaven.test.skip=true;
note "Building exchange-currency DONE"; 
cd ..;
cd exchange-calculator;
note "Building exchange-calculator..."; 
./mvnw clean package -Dmaven.test.skip=true;
note "Building exchange-calculator DONE"; 
cd ..;
cd exchange-discovery;
note "Building exchange-discovery..."; 
./mvnw clean package;
note "Building exchange-discovery DONE"; 
cd ..;
cd exchange-gateway;
note "Building exchange-gateway..."; 
./mvnw clean package;
note "Building exchange-gateway DONE"; 
cd ..;
