package com.anchal.microservices.currencyexchangeservice;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
//    @Retry(name = "default")
//    @Retry(name = "sample-api", fallbackMethod = "failedMessageRes")
    @CircuitBreaker(name = "default", fallbackMethod = "failedMessageRes")
    public String sampleAPi(){
        System.out.println("trying");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://dummylink:8080/dum", String.class);

        return forEntity.getBody();
    }

    public String failedMessageRes(Exception exception){
        return "Failed sorry " + exception.getMessage();
    }
}
