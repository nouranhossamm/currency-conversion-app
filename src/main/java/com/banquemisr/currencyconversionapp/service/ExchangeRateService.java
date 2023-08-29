package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.props.AppProps;
import com.banquemisr.currencyconversionapp.validation.AmountValidation;
import com.banquemisr.currencyconversionapp.validation.CurrencyExistsValidation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableConfigurationProperties(value = AppProps.class)
@CacheConfig(cacheNames = "ConCurrency")
public class ExchangeRateService {
    private final ExchangeRateAPIClient exchangeRateAPIClient;
    private final AppProps appProps;
    private final AmountValidation amountValidation;
    private final CurrencyExistsValidation currencyExistsValidation;

    public ExchangeRateService(
            ExchangeRateAPIClient exchangeRateAPIClient,
            AppProps appProps,
            AmountValidation amountValidation) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
        this.appProps = appProps;
        this.amountValidation = amountValidation;

        List<String> codes = appProps.getCurrencies().stream().map(CurrencyDTO::code).toList();

        this.currencyExistsValidation = new CurrencyExistsValidation(codes);
    }

    @Cacheable
    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.appProps.getCurrencies();
    }

    @Cacheable
    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        System.out.println("Not Cached");
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    @Cacheable
    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        System.out.println("Not Cached");
        amountValidation.validate(amount);
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

    public ComparisonDTO currencyComparison(String current, List<String> targets) {
        System.out.println("Not Cached");
        currencyExistsValidation.validate(current);

        System.out.println(0);
        targets.forEach(this.currencyExistsValidation::validate);

        System.out.println(1);
        ComparisonDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        System.out.println(2);
        if (!response.result().equals("success")) {
            System.out.println(3);
            return ComparisonDTO
                    .builder()
                    .result("failure")
                    .build();
        }

        Map<String, Double> filteredConversionRates = new HashMap<>();
        Map<String, Double> conversionRates = response.conversionRates();

        System.out.println(4);
        targets.forEach(target -> {
            if (conversionRates.containsKey(target)) {
                filteredConversionRates.put(target, conversionRates.get(target));
            }
        });
        System.out.println(5);

        return ComparisonDTO
            .builder()
            .result("success")
            .baseCode(response.baseCode())
            .targetCodes(filteredConversionRates.keySet())
            .conversionRates(filteredConversionRates)
            .build();
    }
}
