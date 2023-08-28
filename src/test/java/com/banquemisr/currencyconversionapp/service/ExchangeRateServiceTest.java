package com.banquemisr.currencyconversionapp.service;
//
//import com.banquemisr.currencyconversionapp.client.ExchangeRateAPIClient;
//import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
//import com.banquemisr.currencyconversionapp.dto.UnitCurrencyConversionDTO;
//import com.banquemisr.currencyconversionapp.props.AppProps;
//import com.banquemisr.currencyconversionapp.validation.AmountValidation;
//import com.banquemisr.currencyconversionapp.validation.Validate;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {
//    @Mock
//    private final ExchangeRateAPIClient exchangeRateAPIClient;
//    @Mock
//    private final AppProps appProps;
//
//    private final ExchangeRateService exchangeRateService;
//
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
//
//
//    @Test
//    void itShouldGetAvailableCurrencies() {
//        Set<CurrencyDTO> availableCurrencies = this.exchangeRateService.getAvailableCurrencies();
//
//        Set<CurrencyDTO> currencies = verify(appProps).getCurrencies();
//
//        assertThat(availableCurrencies).isNotNull();
//        assertThat(availableCurrencies).isNotEmpty();
//    }
//
//    @Test
//    void itShouldReturnCurrencyConversionRate() {
//        String current = "EGP";
//        String target = "USD";
//
//        UnitCurrencyConversionDTO unitCurrencyConversionDTO =
//                this.exchangeRateService.currencyConversion(current, target);
//
//        verify(exchangeRateAPIClient).getCurrencyConversion(current, target);
//
//        assertThat(unitCurrencyConversionDTO).isNotNull();
//    }
//
    @Test
    void testCurrencyConversion() {
    }
//
//    @Test
//    void getExchangeRate() {
//    }
//
//    @Test
//    void currencyComparison() {
//    }
}