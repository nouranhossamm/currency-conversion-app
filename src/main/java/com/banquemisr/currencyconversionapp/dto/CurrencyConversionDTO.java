// Nouran

package com.banquemisr.currencyconversionapp.dto;

import lombok.Builder;

@Builder
public record CurrencyConversionDTO(
	String base_code,
	String target_code,
	Double conversion_rate,
	Double conversion_result
) {
}
