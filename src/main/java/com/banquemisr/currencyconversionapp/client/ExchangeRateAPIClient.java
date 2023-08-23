package com.banquemisr.currencyconversionapp.client;

import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ExchangeRateAPI", url = "${external-api}")
public interface ExchangeRateAPIClient {
    // Example
    @RequestMapping(method = RequestMethod.GET, value = "/latest/{currency_code}")
    ExchangeRateDataDTO getCurrencyInfo(@PathVariable("currency_code") String currency_code);
}
