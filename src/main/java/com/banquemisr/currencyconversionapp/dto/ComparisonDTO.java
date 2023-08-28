package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.Map;
import java.util.Set;

@Builder
public record ComparisonDTO(
    @JsonProperty("result")
    String result,
    @JsonProperty("base_code")
    String baseCode,
    @JsonProperty("target_codes")
    Set<String> targetCodes,
    @JsonProperty("conversion_rates")
    Map<String, Double> conversionRates
) {
}
