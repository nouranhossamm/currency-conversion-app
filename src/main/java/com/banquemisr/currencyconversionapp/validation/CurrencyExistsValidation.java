package com.banquemisr.currencyconversionapp.validation;

import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import com.banquemisr.currencyconversionapp.exception.NotFoundException;
import com.banquemisr.currencyconversionapp.props.AppProps;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CurrencyExistsValidation implements Validate<String> {
    private final List<String> currencyDTOList;
    public CurrencyExistsValidation(List<String> currencyDTOList) {
        this.currencyDTOList = currencyDTOList;
    }

    @Override
    public void validate(String current) {
        Optional<String> expectedCurrency = currencyDTOList.stream()
                .filter(code -> Objects.equals(code, current)).findFirst();

        if (expectedCurrency.isEmpty()) {
            throw new NotFoundException("Currency not found.");
        }
    }
}
