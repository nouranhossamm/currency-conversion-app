// Nouran

package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;

    public CurrencyConversionService(ExchangeRateAPIClient exchangeRateAPIClient) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
    }

    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        return exchangeRateAPIClient.getCurrencyConversion(current, target, amount);
    }
}
