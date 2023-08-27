package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.exception.NotFoundException;
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
    private final List<String> codes;
    private final CurrencyExistsValidation currencyExistsValidation;
    public ExchangeRateService(
            ExchangeRateAPIClient exchangeRateAPIClient,
            AppProps appProps,
            AmountValidation amountValidation) {
        this.exchangeRateAPIClient = exchangeRateAPIClient;
        this.appProps = appProps;
        this.amountValidation = amountValidation;
        this.codes = new ArrayList<>();

        for (CurrencyDTO code : appProps.getCurrencies()) {
            this.codes.add(code.code());
        }
        this.currencyExistsValidation = new CurrencyExistsValidation(codes);
    }

//    @Cacheable(value = "currencies", key = "#root.methodName")
    public Set<CurrencyDTO> getAvailableCurrencies() {
        System.out.println("Redis not used");
        return this.appProps.getCurrencies();
    }

    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        amountValidation.validate(amount);
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

//    @Cacheable(value = "currencies", key = "#root.methodName")
    public ExchangeRateDataDTO getExchangeRate(String current) {
        System.out.println("Redis not used");
        currencyExistsValidation.validate(current);
        return this.exchangeRateAPIClient.getCurrencyInfo(current);
    }

    public ExchangeRateDataDTO currencyComparison(String current, List<String> targets) {
        currencyExistsValidation.validate(current);
        for (String target: targets){
            currencyExistsValidation.validate(target);
        }
        ExchangeRateDataDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        if (response.result().equals("success")) {
            Map<String, Double> filteredConversionRates = new HashMap<>();
            String baseCode = response.baseCode();
            Map<String, Double> conversionRates = response.conversionRates();

            for (String target : targets) {
                if (conversionRates.containsKey(target)) {
                    filteredConversionRates.put(target, conversionRates.get(target));
                }
            }

            return ExchangeRateDataDTO
                .builder()
                .result("success")
                .baseCode(baseCode)
                .targetCodes(new ArrayList<>(filteredConversionRates.keySet()))
                .conversionRates(filteredConversionRates)
                .build();
        }

        return ExchangeRateDataDTO
            .builder()
            .result("failure")
            .build();
    }
}
