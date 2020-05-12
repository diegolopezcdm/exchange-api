#!/bin/bash

cd exchange-config;
./mvnw clean;
cd ..;
cd exchange-currency;
./mvnw clean;
cd ..;
cd exchange-calculator;
./mvnw clean;
cd ..;
cd exchange-discovery;
./mvnw clean;
cd ..;
cd exchange-gateway;
./mvnw clean;
cd ..;
cd exxhange-security;
./mvnw clean;
cd ..;