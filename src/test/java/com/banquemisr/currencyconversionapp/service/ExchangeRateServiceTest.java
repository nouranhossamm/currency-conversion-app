package com.banquemisr.currencyconversionapp.service;

import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ExchangeRateServiceTest {
//    @Mock
//    private ExchangeRateAPIClient exchangeRateAPIClient;
//    @Autowired
//    @Mock
//    private AppProps appProps;
//    @Mock
//    private AmountValidation amountValidation;
//    @Mock
//    private CurrencyExistsValidation currencyExistsValidation;

    @Autowired
    private ExchangeRateService exchangeRateService;

//    @BeforeEach
//    public void setUp() {
//        this.exchangeRateService = new ExchangeRateService(exchangeRateAPIClient, appProps, amountValidation);
//    }

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
//        when(appProps.getCurrencies()).thenReturn(Collections.singleton(new CurrencyDTO("US Dollar", "USD", "")));

        Set<CurrencyDTO> availableCurrencies = this.exchangeRateService.getAvailableCurrencies();
//
//        verify(appProps).getCurrencies();

        assertThat(availableCurrencies).isNotNull();
        assertThat(availableCurrencies).isNotEmpty();
    }

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

//    @Test
//    void testCurrencyConversion() {
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
//    }


//    @Test
//    void getExchangeRate() {
//    }
//
//    @Test
//    void currencyComparison() {
//    }
}