package com.banquemisr.currencyconversionapp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Builder
public record ExchangeRateDataDTO(
	String result,
	String base_code,
	List<String> target_codes,
	Map<String, Double> conversion_rates
)
{}
