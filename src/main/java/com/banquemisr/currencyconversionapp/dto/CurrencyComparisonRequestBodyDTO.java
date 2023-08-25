package com.banquemisr.currencyconversionapp.dto;

import lombok.*;

import java.util.List;

@Builder
public record CurrencyComparisonRequestBodyDTO(
    String base_code,
    List<String> target_codes
)
{}
