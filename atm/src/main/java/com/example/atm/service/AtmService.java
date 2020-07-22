package com.example.atm.service;

import com.example.atm.model.AccountViewer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AtmService {

    private RestTemplate restTemplate = new RestTemplate();

    public AccountViewer deposit(String username, int depositAmount) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8082/" + username + "/deposit?depositAmount=" + depositAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, entity, AccountViewer.class).getBody();

    }

    public AccountViewer withdraw(String username, int withdrawalAmount) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        String url = "http://localhost:8082/" + username + "/withdraw?withdrawalAmount=" + withdrawalAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, entity, AccountViewer.class).getBody();
    }
}
