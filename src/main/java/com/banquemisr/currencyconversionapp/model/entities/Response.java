package com.banquemisr.currencyconversionapp.model.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    private Integer status_code;
    private String status;
    private String message;
    private T data;
}
