package com.banquemisr.currencyconversionapp.web.controllers;


import com.banquemisr.currencyconversionapp.model.entities.ExchangeRateData;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/exchange")
public class ExchangeRateController {
    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/rate/{current}/{target}/{amount}")
    public Mono<ExchangeRateData> getCurrencyConversion(
            @PathVariable String current,
            @PathVariable String target,
            @PathVariable Double amount){
        return exchangeRateService.currencyConversion(current, target, amount);
    }

    @GetMapping("/rate/{current}")
    public Mono<Map<String, Object>> getExchangeRate(
            @PathVariable String current){
        return exchangeRateService.getExchangeRate(current);
    }
}

