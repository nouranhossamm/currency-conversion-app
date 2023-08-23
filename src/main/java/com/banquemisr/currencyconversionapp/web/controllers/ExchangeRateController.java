package com.banquemisr.currencyconversionapp.web.controllers;

import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/currencies")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping("{current}/{target}/{amount}")
    public ExchangeRateDataDTO getCurrencyConversion(
            @PathVariable("current") String current,
            @PathVariable("target") String target,
            @PathVariable("amount") Double amount
    ) {
//        return this.exchangeRateService.currencyConversion(current, target, amount);
        return null;
    }

    @GetMapping("{current}")
    public ExchangeRateDataDTO getExchangeRate(@PathVariable("current") String current){
        return this.exchangeRateService.getExchangeRate(current);
    }
}
