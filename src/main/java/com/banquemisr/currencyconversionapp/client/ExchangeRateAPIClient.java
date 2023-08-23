package com.banquemisr.currencyconversionapp.client;

import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.ExchangeRateDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "ExchangeRateAPI", url = "https://v6.exchangerate-api.com/v6/ecf10bab01b34bf0de9636e1")
public interface ExchangeRateAPIClient {
    // Example
    @RequestMapping(method = RequestMethod.GET, value = "/latest/{currency_code}")
    ExchangeRateDataDTO getCurrencyInfo(@PathVariable("currency_code") String currency_code);

//    Nouran
@GetMapping("pair/{current}/{target}/{amount}")
CurrencyConversionDTO getCurrencyConversion(@PathVariable String current, @PathVariable String target, @PathVariable Double amount);

}
