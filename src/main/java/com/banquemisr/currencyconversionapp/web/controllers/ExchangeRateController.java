package com.banquemisr.currencyconversionapp.web.controllers;

import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("api/v1/currencies")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.exchangeRateService.getAvailableCurrencies();
    }

    @GetMapping("/{current}/{target}")
    public UnitCurrencyConversionDTO getCurrencyConversion(
        @PathVariable("current") String current,
        @PathVariable("target") String target
    ) {
        return this.exchangeRateService.currencyConversion(current, target);
    }

    @GetMapping("/{current}/{target}/{amount}")
    public CurrencyConversionDTO getCurrencyConversionWithAmount(
        @PathVariable("current") String current,
        @PathVariable("target") String target,
        @PathVariable("amount") Double amount
    ) {
        return this.exchangeRateService.currencyConversion(current, target, amount);
    }

    @GetMapping("/{current}")
    public ExchangeRateDataDTO getExchangeRate(@PathVariable("current") String current){
        return this.exchangeRateService.getExchangeRate(current);
    }
}
