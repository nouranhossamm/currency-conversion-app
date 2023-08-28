package com.banquemisr.currencyconversionapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Entry point of Spring Application
 * @author Muhammad Bassiouni
 */
@SpringBootApplication
@EnableFeignClients
@EnableCaching
public class CurrencyConversionAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionAppApplication.class, args);
    }

}
