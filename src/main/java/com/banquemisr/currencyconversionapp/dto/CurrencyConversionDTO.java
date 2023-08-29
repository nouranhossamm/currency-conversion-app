// Nouran

package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record CurrencyConversionDTO(
	@JsonProperty("time_next_update_unix")
	@JsonIgnore
	Long timeNextUpdateUnix,
	@JsonProperty("base_code")
	String baseCode,
	@JsonProperty("target_code")
	String targetCode,
	@JsonProperty("conversion_rate")
	Double conversionRate,
	@JsonProperty("conversion_result")
	Double conversionResult
) implements Serializable {
}
