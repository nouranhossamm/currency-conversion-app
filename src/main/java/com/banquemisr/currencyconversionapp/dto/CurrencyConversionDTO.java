// Nouran

package com.banquemisr.currencyconversionapp.dto;

import lombok.Data;

@Data
public class CurrencyConversionDTO {
    private String base_code;
    private String target_code;
    private Double conversion_rate;
    private Double conversion_result;
}
