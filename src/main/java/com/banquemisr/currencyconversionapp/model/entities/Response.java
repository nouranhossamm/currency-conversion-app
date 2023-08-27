package com.banquemisr.currencyconversionapp.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(
    @JsonProperty("status_code")
    Integer statusCode,
    String status,
    String message,
    T data
) {
}
