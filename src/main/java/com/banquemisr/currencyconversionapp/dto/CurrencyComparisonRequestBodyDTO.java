package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Builder
public record CurrencyComparisonRequestBodyDTO(
    @JsonProperty("base_code")
    String baseCode,
    @JsonProperty("target_codes")
    List<String> targetCodes
) {
}
