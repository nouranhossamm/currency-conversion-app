package com.banquemisr.currencyconversionapp.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRateData {
    private String result;
    private String base_code;
    private String target_code;
    private double conversion_rate;
    private double conversion_result;
    private Object conversion_rates;
}
