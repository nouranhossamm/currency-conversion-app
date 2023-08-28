package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
public record CurrencyDTO(
        String name,
        String code,
        @JsonProperty("icon_url")
        String iconUrl
) implements Serializable {
}
