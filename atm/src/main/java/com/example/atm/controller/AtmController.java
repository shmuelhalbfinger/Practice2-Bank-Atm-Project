package com.example.atm.controller;

import com.example.atm.model.DepositRequest;
import com.example.atm.model.TransactionResult;
import com.example.atm.model.WithdrawRequest;
import com.example.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {
    @Autowired
    private AtmService service;

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public TransactionResult withdraw(@RequestBody WithdrawRequest request) {
        return service.withdraw(request);
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public TransactionResult deposit(@RequestBody DepositRequest request) {
        return service.deposit(request);
    }

    @RequestMapping(value = "/sayHello", method = RequestMethod.POST)
    public String sayHello(@RequestBody String name) {
        return service.sayHello(name);
    }
}
