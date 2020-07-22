package com.example.atm.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AtmService {

    private final RestTemplate restTemplate = new RestTemplate();
}
