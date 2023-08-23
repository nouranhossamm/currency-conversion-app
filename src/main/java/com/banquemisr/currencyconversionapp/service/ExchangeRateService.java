package com.banquemisr.currencyconversionapp.service;


import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;

    public ExchangeRateService(ExchangeRateAPIClient exchangeRateAPIClient) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
    }

//    public Object /*ExchangeRateDataDTO*/ currencyConversion(String current, String target, Double amount) {
//        // TODO: Implement
//        return 0;
//    }


    public ExchangeRateDataDTO getExchangeRate(String current){
        return this.exchangeRateAPIClient.getCurrencyInfo(current);
    }
}
