package com.banquemisr.currencyconversionapp.web.controllers;

import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.model.entities.Response;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("api/v1/currencies")
@CrossOrigin(origins = "*/*", maxAge = 3600)
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping
    public ResponseEntity<Response<Set<CurrencyDTO>>> getAvailableCurrencies() {
        Set<CurrencyDTO> currencyDTOS = this.exchangeRateService.getAvailableCurrencies();
        Response<Set<CurrencyDTO>> response = new Response<>(200,
                "success",
                "Currency list retrieved successfully",
                currencyDTOS);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{current}/{target}")
    public ResponseEntity<Response<UnitCurrencyConversionDTO>> getCurrencyConversion(
        @PathVariable("current") String current,
        @PathVariable("target") String target
    ) {
        UnitCurrencyConversionDTO currencyConversion = this.exchangeRateService.currencyConversion(current, target);
        Response<UnitCurrencyConversionDTO> response = new Response<>(200,
                "success",
                "Currency converted successfully",
                currencyConversion);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{current}/{target}/{amount}")
    public ResponseEntity<Response<CurrencyConversionDTO>> getCurrencyConversionWithAmount(
        @PathVariable("current") String current,
        @PathVariable("target") String target,
        @PathVariable("amount") Double amount
    ) {
        CurrencyConversionDTO conversionDTO = this.exchangeRateService.currencyConversion(current, target, amount);
        Response<CurrencyConversionDTO> response = new Response<>(200,
                "success",
                "Currency converted successfully with the provided amount",
                conversionDTO);
        return ResponseEntity.ok(response);
    }

     @GetMapping("{current}")
    public ResponseEntity<Response<ExchangeRateDataDTO>> getExchangeRate(@PathVariable("current") String current){
        ExchangeRateDataDTO rateDataDTO = this.exchangeRateService.getExchangeRate(current);
         Response<ExchangeRateDataDTO> response = new Response<>(200,
                 "success",
                 "Currency retrieved successfully",
                 rateDataDTO);
         return ResponseEntity.ok(response);
    }

    @GetMapping("comparison")
    public ResponseEntity<Response<ExchangeRateDataDTO>> getCurrencyComparison(
        @RequestBody CurrencyComparisonRequestBodyDTO requestBodyDTO
    ) {
        ExchangeRateDataDTO exchangeRateDataDTO = this.exchangeRateService.currencyComparison(
            requestBodyDTO.baseCode(),
            requestBodyDTO.targetCodes()
        );
        Response<ExchangeRateDataDTO> response = new Response<>(200,
                "success",
                "Comparison done successfully",
                exchangeRateDataDTO);
        return ResponseEntity.ok(response);
    }
}
