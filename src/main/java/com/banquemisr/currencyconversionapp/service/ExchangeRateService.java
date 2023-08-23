package com.banquemisr.currencyconversionapp.service;


import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.props.AppProps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@EnableConfigurationProperties(value = AppProps.class)
public class ExchangeRateService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;
    private final AppProps appProps;

    private final Set<CurrencyDTO> currencyDTOSet;

    public ExchangeRateService(ExchangeRateAPIClient exchangeRateAPIClient, AppProps appProps) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
        this.appProps = appProps;
        this.currencyDTOSet = this.appProps.getCurrencies();
    }

    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.currencyDTOSet;
    }

    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }
    public ExchangeRateDataDTO getExchangeRate(String current){
        return this.exchangeRateAPIClient.getCurrencyInfo(current);
    }
}
