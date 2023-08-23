package com.banquemisr.currencyconversionapp.dto;

import lombok.*;

@Builder
public record CurrencyDTO(String name, String code, String icon_url) {
}
