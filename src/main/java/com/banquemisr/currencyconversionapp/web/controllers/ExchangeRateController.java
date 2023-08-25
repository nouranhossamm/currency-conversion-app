package com.banquemisr.currencyconversionapp.web.controllers;

import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("{current}/{target}")
    public UnitCurrencyConversionDTO getCurrencyConversion(
        @PathVariable("current") String current,
        @PathVariable("target") String target
    ) {
        return this.exchangeRateService.currencyConversion(current, target);
    }

    @GetMapping("{current}/{target}/{amount}")
    public CurrencyConversionDTO getCurrencyConversionWithAmount(
        @PathVariable("current") String current,
        @PathVariable("target") String target,
        @PathVariable("amount") Double amount
    ) {
        return this.exchangeRateService.currencyConversion(current, target, amount);
    }

    @GetMapping("{current}")
    public ExchangeRateDataDTO getExchangeRate(@PathVariable("current") String current){
        return this.exchangeRateService.getExchangeRate(current);
    }

    @GetMapping("comparison")
    public ExchangeRateDataDTO getCurrencyComparison(
            @RequestBody CurrencyComparisonRequestBodyDTO requestBodyDTO
    ) {
        return this.exchangeRateService.currencyComparison(
                requestBodyDTO.base_code(), requestBodyDTO.target_codes());
    }
}

//error handling
//response entity
//validation