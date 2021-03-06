package com.example.bank.controller;

import com.example.bank.mapper.AccountMapper;
import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;


    @PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account createAccount(@RequestBody Account account) {
        return accountMapper.mapToAccount(accountService.createAccount(accountMapper.mapToAccountEntity(account)));
    }

    @PutMapping(value = "/account/{username}/editName", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account updateAccountName(@PathVariable("username") String username, @RequestParam String editName) {
        return accountMapper.mapToAccount(accountService.updateAccountName(username, editName));
    }

    @GetMapping(value = "/account/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account viewAccount(@PathVariable("username") String username) {
        return accountMapper.mapToAccount(accountService.viewAccount(username));
    }

    @PutMapping(value = "/account/{username}/deposit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account deposit(@PathVariable("username") String username, @RequestParam int depositAmount) {
        return accountMapper.mapToAccount(accountService.deposit(username, depositAmount));
    }

    @PutMapping(value = "/account/{username}/withdraw", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account withdraw(@PathVariable("username") String username, @RequestParam int withdrawalAmount) {
        return accountMapper.mapToAccount(accountService.withdraw(username, withdrawalAmount));
    }

    @GetMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Account> viewAllAccounts() {
        return accountService.viewAllAccounts().stream().map(accountMapper::mapToAccount).collect(Collectors.toList());
    }

    @DeleteMapping(value = "/account/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Account deleteAccount(@PathVariable("username") String username) {
        return accountMapper.mapToAccount(accountService.deleteAccount(username));
    }
}
