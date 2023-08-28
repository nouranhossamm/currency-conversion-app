package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.props.AppProps;
import com.banquemisr.currencyconversionapp.validation.AmountValidation;
import com.banquemisr.currencyconversionapp.validation.CurrencyExistsValidation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableConfigurationProperties(value = AppProps.class)
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
        List<String> codes = new ArrayList<>();

        for (CurrencyDTO code : appProps.getCurrencies()) {
            codes.add(code.code());
        }
        this.currencyExistsValidation = new CurrencyExistsValidation(codes);
    }

    @Cacheable("list")
    public Set<CurrencyDTO> getAvailableCurrencies() {
        System.out.println("Redis not used");
        return this.appProps.getCurrencies();
    }

    @Cacheable("currency_conversion")
    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        amountValidation.validate(amount);
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

    public ComparisonDTO currencyComparison(String current, List<String> targets) {
        currencyExistsValidation.validate(current);

        targets.forEach(this.currencyExistsValidation::validate);

        ComparisonDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        if (response.result().equals("success")) {
            Map<String, Double> filteredConversionRates = new HashMap<>();
            Map<String, Double> conversionRates = response.conversionRates();

            targets.forEach(target -> {
                if (conversionRates.containsKey(target)) {
                    filteredConversionRates.put(target, conversionRates.get(target));
                }
            });

            return ComparisonDTO
                .builder()
                .result("success")
                .baseCode(response.baseCode())
                .targetCodes(filteredConversionRates.keySet())
                .conversionRates(filteredConversionRates)
                .build();
        }

        return ComparisonDTO
            .builder()
            .result("failure")
            .build();
    }
}
