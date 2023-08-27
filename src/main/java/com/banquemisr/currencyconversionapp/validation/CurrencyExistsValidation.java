//package com.banquemisr.currencyconversionapp.validation;
//
//import com.banquemisr.currencyconversionapp.dto.CurrencyDTO;
//import com.banquemisr.currencyconversionapp.exception.NotFoundException;
//import com.banquemisr.currencyconversionapp.props.AppProps;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//public class CurrencyExistsValidation {
//    private final AppProps appProps;
//    public CurrencyExistsValidation(String current, AppProps appProps) {
//        this.appProps = appProps;
//        List<String> codes = new ArrayList<>();
//
//        for (CurrencyDTO code : this.appProps.getCurrencies()) {
//            codes.add(code.code());
//        }
//
//        Optional<String> expectedCurrency = codes.stream().filter(code -> Objects.equals(code, current)).findFirst();
//
//        if (expectedCurrency.isEmpty()) {
//            throw new NotFoundException("Currency not found.");
//        }
//    }
//}
