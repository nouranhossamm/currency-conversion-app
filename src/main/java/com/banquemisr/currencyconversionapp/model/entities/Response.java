package com.banquemisr.currencyconversionapp.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record Response<T>(
    Integer status_code,
    String status,
    String message,
    T data
) {
}
