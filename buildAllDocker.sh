#!/bin/bash

function note() {
    local GREEN NC
    GREEN='\033[0;32m'
    NC='\033[0m' # No Color
    printf "\n${GREEN}$@  ${NC}\n" >&2
}

cd exchange-config;
note "Building exchange-config..."; 
docker build -t exchangueconfig .
note "Building exchange-config DONE"; 
cd ..;
cd exchange-currency;
note "Building exchange-currency..."; 
docker build -t exchanguecurrency .
note "Building exchange-currency DONE"; 
cd ..;
cd exchange-calculator;
note "Building exchange-calculator..."; 
docker build -t exchanguecalculator .
note "Building exchange-calculator DONE"; 
cd ..;
cd exchange-discovery;
note "Building exchange-discovery..."; 
docker build -t exchanguediscovey .
note "Building exchange-discovery DONE"; 
cd ..;
cd exchange-gateway;
note "Building exchange-gateway..."; 
docker build -t exchanguegateway .
note "Building exchange-gateway DONE"; 
cd ..;
cd exxhange-security;
note "Building exchange-security..."; 
docker build -t exchanguesecurity .
note "Building exchange-security DONE"; 
cd ..;