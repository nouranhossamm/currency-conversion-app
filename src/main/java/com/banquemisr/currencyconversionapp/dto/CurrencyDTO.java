package com.banquemisr.currencyconversionapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

// The code snippet defines a Java record class called `CurrencyDTO`. A record is a feature 
// that provides a concise way to declare classes that are primarily used to store data.
@Builder
public record CurrencyDTO(
        String name,
        String code,
        @JsonProperty("icon_url")
        String iconUrl
) implements Serializable {
}
