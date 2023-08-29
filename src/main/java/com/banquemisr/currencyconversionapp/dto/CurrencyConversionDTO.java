// Nouran

package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.io.Serializable;

/** 
 * DTO for API Response used in Conversion
 * @param basecode base currency code
 * @param targetcode target currency code
 * @param conversionrate the conversion rate
 * @param conversionresult the conversion result
*/
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
) implements Serializable {
}
