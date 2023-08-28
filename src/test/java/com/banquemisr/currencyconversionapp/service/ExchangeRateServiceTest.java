package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
import com.banquemisr.currencyconversionapp.dto.CurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
import com.banquemisr.currencyconversionapp.props.AppProps;
import com.banquemisr.currencyconversionapp.validation.AmountValidation;
import com.banquemisr.currencyconversionapp.validation.CurrencyExistsValidation;
import com.banquemisr.currencyconversionapp.validation.Validate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {
    @Mock
    private ExchangeRateAPIClient exchangeRateAPIClient;
    @Mock
    private AppProps appProps;
    @Mock
    private AmountValidation amountValidation;
    @Mock
    private CurrencyExistsValidation currencyExistsValidation;

    @InjectMocks
    private ExchangeRateService exchangeRateService;

//    ExchangeRateServiceTest(
//            ExchangeRateAPIClient exchangeRateAPIClient,
//            AppProps appProps
//    ) {
//        this.exchangeRateAPIClient = exchangeRateAPIClient;
//        this.appProps = appProps;
//
//        AmountValidation validator = new AmountValidation();
//        this.exchangeRateService =
//                new ExchangeRateService(exchangeRateAPIClient, appProps, validator);
//    }


    @Test
    void itShouldGetAvailableCurrencies() {

        Set<CurrencyDTO> availableCurrencies = this.exchangeRateService.getAvailableCurrencies();
//
        verify(appProps, times(2)).getCurrencies();

        System.out.println(availableCurrencies);
        assertThat(availableCurrencies).isNotNull();
        assertThat(availableCurrencies).isNotEmpty();
    }

    @Test
    void itShouldReturnCurrencyConversionRate() {
//        String current = "EGP";
//        String target = "USD";
//
//        UnitCurrencyConversionDTO unitCurrencyConversionDTO =
//                this.exchangeRateService.currencyConversion(current, target);
//
//        verify(exchangeRateAPIClient).getCurrencyConversion(current, target);
//
//        assertThat(unitCurrencyConversionDTO).isNotNull();
    }

    @Test
    void testCurrencyConversion() {
//        String current = "EGP";
//        String target = "USD";
//        Double amount = 100.0;
//
//        // Mock the behavior of validators
//        Mockito.doNothing().when(amountValidation).validate(amount);
//        Mockito.doNothing().when(currencyExistsValidation).validate(current);
//        Mockito.doNothing().when(currencyExistsValidation).validate(target);
//
//        // Mock the behavior of API client
//        CurrencyConversionDTO mockCurrencyConversionDTO = CurrencyConversionDTO.builder()
//                .baseCode(current)
//                .targetCode(target)
//                .conversionRate(1.0)
//                .conversionResult(100.0)
//                .build();
//        Mockito.when(exchangeRateAPIClient.getCurrencyConversionWithAmount(current, target, amount))
//                .thenReturn(mockCurrencyConversionDTO);
//
//        CurrencyConversionDTO currencyConversionDTO =
//                exchangeRateService.currencyConversion(current, target, amount);
//
//        // Verify method calls
//        verify(exchangeRateAPIClient).getCurrencyConversionWithAmount(current, target, amount);
//        verify(amountValidation).validate(amount);
//        verify(currencyExistsValidation).validate(current);
//        verify(currencyExistsValidation).validate(target);
//
//        assertThat(currencyConversionDTO).isNotNull();
    }


    @Test
    void getExchangeRate() {
    }

    @Test
    void currencyComparison() {
    }
}