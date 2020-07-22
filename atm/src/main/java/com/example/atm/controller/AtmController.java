package com.example.atm.controller;

import com.example.atm.service.AtmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtmController {
    @Autowired
    private AtmService service;
}
