package com.anchal.microservices.limitsservice.controller;

import com.anchal.microservices.limitsservice.dto.Limits;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsController {

    @GetMapping("/limits")
    public Limits listLimit(){
        return new Limits(1, 1000);
    }
}
