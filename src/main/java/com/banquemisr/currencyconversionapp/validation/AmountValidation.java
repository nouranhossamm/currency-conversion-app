package com.banquemisr.currencyconversionapp.validation;

import com.banquemisr.currencyconversionapp.exception.BadEntryException;
import org.springframework.stereotype.Component;

@Component
public class AmountValidation {
    public void validate(Double amount) {
        if (amount <= 0) {
            throw new BadEntryException("The amount should be more than 0");
        }
    }
}
