package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.*;
import com.banquemisr.currencyconversionapp.props.AppProps;
import com.banquemisr.currencyconversionapp.validation.AmountValidation;
import com.banquemisr.currencyconversionapp.validation.CurrencyExistsValidation;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@EnableConfigurationProperties(value = AppProps.class)
@CacheConfig(cacheNames = "ConCurrency")
@Log4j2
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

    @Caching(
            cacheable = @Cacheable,
            evict = @CacheEvict(
                    condition = "#root.caches[0].timeNextUpdateUnix() < T(java.lang.System).currentTimeMillis()",
                    allEntries = true
            )
    )
    public Set<CurrencyDTO> getAvailableCurrencies() {
        return this.appProps.getCurrencies();
    }

    @Caching(
        cacheable = @Cacheable,
        evict = @CacheEvict(
            condition = "#root.caches[0].timeNextUpdateUnix() < T(java.lang.System).currentTimeMillis()",
            allEntries = true
        )
    )
    public UnitCurrencyConversionDTO currencyConversion(String current, String target) {
        System.out.println("Not Cached");
        return this.exchangeRateAPIClient.getCurrencyConversion(current, target);
    }

    @Caching(
            cacheable = @Cacheable,
            evict = @CacheEvict(
                    condition = "(!root.caches.empty) && root.caches[0].get", //[0].timeNextUpdateUnix() < T(java.lang.System).currentTimeMillis()",
                    allEntries = true
            )
    )
    public CurrencyConversionDTO currencyConversion(String current, String target, Double amount) {
        System.out.println("Not Cached");
        amountValidation.validate(amount);
        return this.exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount);
    }

    @Caching(
            cacheable = @Cacheable,
            evict = @CacheEvict(
                    condition = "#root.caches[0].timeNextUpdateUnix() < T(java.lang.System).currentTimeMillis()",
                    allEntries = true
            )
    )
    public ComparisonDTO currencyComparison(String current, List<String> targets) {
        System.out.println("Not Cached");
        currencyExistsValidation.validate(current);

        targets.forEach(this.currencyExistsValidation::validate);

        ComparisonDTO response = exchangeRateAPIClient.getCurrencyInfo(current);

        if (!response.result().equals("success")) {
            return ComparisonDTO
                    .builder()
                    .result("failure")
                    .build();
        }

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
}
