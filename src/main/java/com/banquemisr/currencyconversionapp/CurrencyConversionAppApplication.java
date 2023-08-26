package com.banquemisr.currencyconversionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class CurrencyConversionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionAppApplication.class, args);
    }

}

//    http://localhost:8099/swagger-ui/index.html
//    http://localhost:8099/v3/api-docs/
