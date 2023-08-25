package com.banquemisr.currencyconversionapp.service;


import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.props.AppProps;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public ExchangeRateDataDTO currencyComparison(String current, List<String> targets) {
        ExchangeRateDataDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        if (response.result().equals("success")) {
            String baseCode = response.base_code();
            Map<String, Double> conversionRates = response.conversion_rates();

            Map<String, Double> filteredConversionRates = new HashMap<>();

            for (String target : targets) {
                if (conversionRates.containsKey(target)) {
                    filteredConversionRates.put(target, conversionRates.get(target));
                }
            }

            return ExchangeRateDataDTO.builder()
                    .result("success")
                    .base_code(baseCode)
                    .target_codes(filteredConversionRates.keySet().stream().collect(Collectors.toList()))
                    .conversion_rates(filteredConversionRates)
                    .build();
        } else {
            return ExchangeRateDataDTO.builder()
                    .result("failure")
                    .build();
        }
    }

}
