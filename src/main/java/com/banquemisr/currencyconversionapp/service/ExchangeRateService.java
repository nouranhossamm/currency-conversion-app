package com.banquemisr.currencyconversionapp.service;


import com.banquemisr.currencyconversionapp.model.entities.ExchangeRateData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeRateService {
    String api = "https://v6.exchangerate-api.com/v6/92a0f05ef799a5c2339ff864";
    private final WebClient webClient = WebClient.builder()
            .baseUrl(api)
            .build();

    public Mono<ExchangeRateData> currencyConversion(String current, String target, Double amount){
        return webClient.get()
                .uri("pair/{current}/{target}/{amount}", current, target, amount)
                .retrieve()
                .bodyToMono(ExchangeRateData.class)
                .map(response-> {
                    return response;
                })
                //error handling will replace this
                .onErrorReturn(new ExchangeRateData("error", "", "", 0.0, 0.0, null));
    }


    public Mono<Map<String, Object>> getExchangeRate(String current){
        return webClient.get()
                .uri("/latest/{current}", current)
                .retrieve()
                .bodyToMono(ExchangeRateData.class)
                .map(response-> {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("result", response.getResult());
                    resultMap.put("base_code", response.getBase_code());
                    resultMap.put("conversion_rates", response.getConversion_rates());
                    return resultMap;
                })
                //error handling will replace this
                .onErrorReturn(new HashMap<>());
    }
}
