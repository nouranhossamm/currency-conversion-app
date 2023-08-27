package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;
import java.util.Map;

@Builder
public record ExchangeRateDataDTO(
    @JsonProperty("result")
    String result,
    @JsonProperty("base_code")
    String baseCode,
    @JsonProperty("target_codes")
    List<String> targetCodes,
    @JsonProperty("conversion_rates")
    Map<String, Double> conversionRates) {
}
