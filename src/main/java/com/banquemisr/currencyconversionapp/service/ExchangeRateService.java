package com.banquemisr.currencyconversionapp.service;


import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.props.AppProps;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@EnableConfigurationProperties(value = AppProps.class)
public class ExchangeRateService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;
    private final AppProps appProps;

    public ExchangeRateService(ExchangeRateAPIClient exchangeRateAPIClient, AppProps appProps) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
        this.appProps = appProps;
    }

    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.appProps.getCurrencies();
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

        List<String> codes = new ArrayList<>();
        for (CurrencyDTO code : appProps.getCurrencies()){
            codes.add(code.code());
        }
        List<String> filteredList = targets.stream().filter(codes::contains)
                .toList();
        if (filteredList.size() != targets.size()){
            throw new RuntimeException("Not all currencies are in the list");
        }
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
