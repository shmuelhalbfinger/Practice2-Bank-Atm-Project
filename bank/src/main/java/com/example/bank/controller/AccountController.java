package com.example.bank.controller;

import com.example.bank.exceptionhandler.AccountNotFoundException;
import com.example.bank.exceptionhandler.DuplicateAccountException;
import com.example.bank.exceptionhandler.ImproperBalanceException;
import com.example.bank.model.*;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    private final AccountService service = new AccountService();

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreateAccountResult createAccount(@RequestBody CreateAccountRequest request) throws DuplicateAccountException {
        return service.createAccount(request);
    }

    @RequestMapping(value = "/editAccount", method = RequestMethod.POST)
    public EditAccountResult editAccount(@RequestBody EditAccountRequest request) throws AccountNotFoundException {
        return service.editAccount(request);
    }

    @RequestMapping(value = "/viewAccount", method = RequestMethod.POST)
    public ViewAccountResult viewAccount(@RequestBody ViewAccountRequest request) throws AccountNotFoundException {
        return service.viewAccount(request);
    }

    @RequestMapping(value = "/addFunds", method = RequestMethod.POST)
    public AddFundsResult addFunds(@RequestBody AddFundsRequest request) throws AccountNotFoundException {
        return service.addFunds(request);
    }

    @RequestMapping(value = "/subtractFunds", method = RequestMethod.POST)
    public SubtractFundsResult subtractFunds(@RequestBody SubtractFundsRequest request) throws AccountNotFoundException, ImproperBalanceException {
        return service.subtractFunds(request);
    }

    @RequestMapping(value = "/viewAllAccounts", method = RequestMethod.POST)
    public ViewAllAccountsResult viewAllAccounts() {
        return service.viewAllAccounts();
    }

    @RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
    public DeleteAccountResult deleteAccount(@RequestBody DeleteAccountRequest request) throws AccountNotFoundException {
        return service.deleteAccount(request);
    }
}
