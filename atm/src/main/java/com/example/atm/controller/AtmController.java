package com.example.atm.controller;

import com.example.atm.model.AccountViewer;
import com.example.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class AtmController {
    @Autowired
    private AtmService atmService;

    @PutMapping (value = "/{username}/deposit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountViewer deposit(@PathVariable("username") String username, @RequestParam int depositAmount) {
        return atmService.deposit(username, depositAmount);
    }

    @PutMapping (value = "/{username}/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public AccountViewer withdraw(@PathVariable("username") String username, @RequestParam int withdrawalAmount) {
        return atmService.withdraw(username, withdrawalAmount);
    }
}
