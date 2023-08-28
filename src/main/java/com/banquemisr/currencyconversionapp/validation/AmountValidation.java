package com.banquemisr.currencyconversionapp.validation;

import com.banquemisr.currencyconversionapp.exception.BadEntryException;
import org.springframework.stereotype.Component;

/**
 * @author Menna Moataz
 */
@Component
public class AmountValidation implements Validate<Double> {
    @Override
    public void validate(Double amount) {
        if (amount <= 0) {
            throw new BadEntryException("The amount should be more than 0");
        }
    }
}
