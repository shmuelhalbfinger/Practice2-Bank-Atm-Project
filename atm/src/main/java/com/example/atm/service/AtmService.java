package com.example.atm.service;

import com.example.atm.model.AccountViewer;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;

@Service
public class AtmService {

    private RestTemplate restTemplate = new RestTemplate();

    public AccountViewer deposit(String username, int depositAmount) {
        String url = "http://localhost:8082/" + username + "?depositAmount=" + depositAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, null, AccountViewer.class).getBody();

    }

    public AccountViewer withdraw(String username, int withdrawalAmount) {
        String url = "http://localhost:8082/" + username + "?withdrawalAmount=" + withdrawalAmount;
        return restTemplate.exchange(url, HttpMethod.PUT, null, AccountViewer.class).getBody();
    }
}
