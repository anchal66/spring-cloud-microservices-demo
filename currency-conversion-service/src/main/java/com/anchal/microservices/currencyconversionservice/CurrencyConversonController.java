package com.anchal.microservices.currencyconversionservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversonController {

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calcCurrency(@PathVariable String from, @PathVariable String to,
                                           @PathVariable BigDecimal quantity){

        HashMap<String, String> uriVariables = new HashMap<>() ;
        uriVariables.put("from", from);
        uriVariables.put("to", to);
        String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}";
        ResponseEntity<CurrencyConversion> responseEntity =
                new RestTemplate().getForEntity(url, CurrencyConversion.class, uriVariables);
        CurrencyConversion currencyConversion = responseEntity.getBody();

        return new CurrencyConversion(
                currencyConversion.getId(),
                from, to, quantity,
                BigDecimal.ONE, BigDecimal.ONE,
                currencyConversion.getEnvironment()
        );
    }
}
