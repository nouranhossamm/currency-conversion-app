// Nouran

package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CurrencyConversionDTO(
	@JsonProperty("base_code")
	String baseCode,
	@JsonProperty("target_code")
	String targetCode,
	@JsonProperty("conversion_rate")
	Double conversionRate,
	@JsonProperty("conversion_result")
	Double conversionResult
) {
}
