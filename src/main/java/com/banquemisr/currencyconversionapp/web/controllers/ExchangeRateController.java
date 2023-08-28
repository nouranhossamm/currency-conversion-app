package com.banquemisr.currencyconversionapp.web.controllers;

import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.entities.Response;
import com.banquemisr.currencyconversionapp.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
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

        Response<Set<CurrencyDTO>> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency list retrieved successfully",
                currencyDTOS
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("{current}/{target}")
    public ResponseEntity<Response<UnitCurrencyConversionDTO>> getCurrencyConversion(
        @PathVariable("current") String current,
        @PathVariable("target") String target
    ) {
        UnitCurrencyConversionDTO currencyConversion = this.exchangeRateService.currencyConversion(current, target);

        Response<UnitCurrencyConversionDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency converted successfully",
                currencyConversion
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("{current}/{target}/{amount}")
    public ResponseEntity<Response<CurrencyConversionDTO>> getCurrencyConversionWithAmount(
        @PathVariable("current") String current,
        @PathVariable("target") String target,
        @PathVariable("amount") Double amount
    ) {
        CurrencyConversionDTO conversionDTO = this.exchangeRateService.currencyConversion(current, target, amount);

        Response<CurrencyConversionDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency converted successfully with the provided amount",
                conversionDTO
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("{current}")
    public ResponseEntity<Response<ComparisonDTO>> getExchangeRate(@PathVariable("current") String current) {
        ComparisonDTO rateDataDTO = this.exchangeRateService.getExchangeRate(current);

        Response<ComparisonDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Currency retrieved successfully",
                rateDataDTO
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("comparison")
    public ResponseEntity<Response<ComparisonDTO>> getCurrencyComparison(
        @RequestBody CurrencyComparisonRequestBodyPOJO requestBodyDTO
    ) {
        ComparisonDTO comparisonDTO = this.exchangeRateService.currencyComparison(
            requestBodyDTO.baseCode(),
            requestBodyDTO.targetCodes()
        );

        Response<ComparisonDTO> response = new Response<>(
                HttpStatus.OK.value(),
                "success",
                "Comparison done successfully",
                comparisonDTO
        );

        return ResponseEntity.ok(response);
    }
}
