// Nouran

package com.banquemisr.currencyconversionapp.web.controllers;

import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.service.CurrencyConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CurrencyConversionController {
    private final CurrencyConversionService currencyConversionService;

    public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/{current}/{target}/{amount}")
    public CurrencyConversionDTO getCurrencyConversion(@PathVariable String current, @PathVariable String target, @PathVariable Double amount) {
        return currencyConversionService.currencyConversion(current, target, amount);
    }

}
