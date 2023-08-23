package com.banquemisr.currencyconversionapp.dto;

import lombok.Builder;

@Builder
public record UnitCurrencyConversionDTO(
        String base_code,
        String target_code,
        Double conversion_rate
) {
}
