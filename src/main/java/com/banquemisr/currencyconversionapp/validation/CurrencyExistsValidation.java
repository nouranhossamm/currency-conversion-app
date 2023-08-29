package com.banquemisr.currencyconversionapp.validation;

import com.banquemisr.currencyconversionapp.exception.NotFoundException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Validate whether a currency code exists in a given list
 * @author Muhammad Bassiouni
 * @author Menna Moataz
 */
@Getter
@Component
public class CurrencyExistsValidation implements Validate<String> {
    @Setter
    private List<String> currencyDTOList;

    @Override
    public void validate(String current) {
        Optional<String> expectedCurrency = currencyDTOList.stream()
                .filter(code -> Objects.equals(code, current)).findFirst();

        if (expectedCurrency.isEmpty()) {
            throw new NotFoundException("Currency not found.");
        }
    }
}
