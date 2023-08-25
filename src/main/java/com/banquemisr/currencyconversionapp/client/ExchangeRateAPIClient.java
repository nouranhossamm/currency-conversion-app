package com.banquemisr.currencyconversionapp.client;

import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ExchangeRateAPI", url = "${external-api}")
public interface ExchangeRateAPIClient {
    @GetMapping("latest/{currency_code}")
    ExchangeRateDataDTO getCurrencyInfo(@PathVariable("currency_code") String currency_code);

    @GetMapping("pair/{current}/{target}")
    UnitCurrencyConversionDTO getCurrencyConversion(
            @PathVariable("current") String current,
            @PathVariable("target") String target
    );

    @GetMapping("pair/{current}/{target}/{amount}")
    CurrencyConversionDTO getCurrencyConversionWithAmount(
            @PathVariable("current") String current,
            @PathVariable("target") String target,
            @PathVariable("amount") Double amount
    );

}
