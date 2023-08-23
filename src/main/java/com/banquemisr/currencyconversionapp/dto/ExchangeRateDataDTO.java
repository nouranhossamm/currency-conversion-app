package com.banquemisr.currencyconversionapp.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record ExchangeRateDataDTO(
        String result,
        String base_code,
        String target_code,
        double conversion_rate,
        double conversion_result,
        Map<String, Double> conversion_rates
) {
}
