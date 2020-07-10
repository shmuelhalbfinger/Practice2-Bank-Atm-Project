package com.example.atm.service;

import com.example.atm.model.DepositRequest;
import com.example.atm.model.TransactionResult;
import com.example.atm.model.WithdrawRequest;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AtmService {

    private final RestTemplate restTemplate = new RestTemplate();

    public TransactionResult withdraw(WithdrawRequest request) {
        JSONObject obj = new JSONObject();
        obj.put("username", request.getUsername());
        obj.put("withdrawalAmount", request.getWithdrawalAmount());
        String url = "http://localhost:8082/subtractFunds";
        TransactionResult result = restTemplate.postForObject(url, obj, TransactionResult.class);
        return result;
    }

    public TransactionResult deposit(DepositRequest request) {
        JSONObject obj = new JSONObject();
        obj.put("username", request.getUsername());
        obj.put("depositAmount", request.getDepositAmount());
        String url = "http://localhost:8082/addFunds";
        TransactionResult result = restTemplate.postForObject(url, obj, TransactionResult.class);
        return result;
    }

    public String sayHello(String name) {
        return "Hello " + name + "!";
    }
}
