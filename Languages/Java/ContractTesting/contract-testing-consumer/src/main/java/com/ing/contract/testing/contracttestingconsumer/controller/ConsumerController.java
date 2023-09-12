package com.ing.contract.testing.contracttestingconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/calculate")
    public String checkOddAndEven(@RequestParam("number") Integer number) {
        return restTemplate.exchange(RequestEntity.get(URI.create("http://localhost:8090/validate/prime-number?number=" + number)).build(), String.class)
                .getBody();
    }
}
