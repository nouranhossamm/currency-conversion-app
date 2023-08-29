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

/**
 * The ExchangeRateService class is a service that handles exchange rate calculations and validations
 * using an external API and application properties.
 */
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

    // The `getAvailableCurrencies()` method is retrieving a set of available currencies from the
    // `AppProps` object. It returns a `Set` of `CurrencyDTO` objects.
    @Cacheable
    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.appProps.getCurrencies();
    }

    // The `currencyConversion` method is responsible for converting a given currency to another
    // currency. It takes two parameters: `current` (the currency to convert from) and `target` (the
    // currency to convert to).
    @Cacheable
    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        System.out.println("Not Cached");
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    // The `currencyConversion` method is responsible for converting a given currency to another
    // currency with a specified amount. It takes three parameters: `current` (the currency to convert
    // from), `target` (the currency to convert to), and `amount` (the amount to convert).
    @Cacheable
    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        System.out.println("Not Cached");
        amountValidation.validate(amount);
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

    // The `currencyComparison` method in the `ExchangeRateService` class is responsible for comparing
    // the exchange rates between a base currency (`current`) and a list of target currencies
    // (`targets`).
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
