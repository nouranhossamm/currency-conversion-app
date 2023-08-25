package com.banquemisr.currencyconversionapp.model.constants;

import lombok.Getter;

@Getter
public enum Error {
    NOTFOUND_ERROR("Currency not found");

    private final String message;

    Error(String message) {
        this.message = message;
    }
}
