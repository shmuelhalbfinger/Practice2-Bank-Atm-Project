package com.example.bank.service;

import com.example.bank.exceptionhandler.AccountNotFoundException;
import com.example.bank.exceptionhandler.DuplicateAccountException;
import com.example.bank.exceptionhandler.ImproperBalanceException;
import com.example.bank.model.*;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    public CreateAccountResult createAccount(CreateAccountRequest request) throws DuplicateAccountException {
        repository.createAccount(request.getUsername(), request.getName(), request.getInitialAccountBalance());
        Account account = repository.findById(request.getUsername()).get();
        CreateAccountResult result = new CreateAccountResult();
        result.setFromAccount(account);
        return result;
    }

    public EditAccountResult editAccount(EditAccountRequest request) throws AccountNotFoundException {
        repository.editAccountByUsername(request.getUsername(), request.getEditName());
        Account account = repository.findById(request.getUsername()).get();
        EditAccountResult result = new EditAccountResult();
        result.setFromAccount(account);
        return result;
    }

    public ViewAccountResult viewAccount(ViewAccountRequest request) throws AccountNotFoundException {
        Account account = repository.findById(request.getUsername()).get();
        ViewAccountResult result = new ViewAccountResult();
        result.setFromAccount(account);
        return result;
    }

    public AddFundsResult addFunds(AddFundsRequest request) throws AccountNotFoundException {
        repository.addFundsByUsername(request.getUsername(), request.getDepositAmount());
        Account account = repository.findById(request.getUsername()).get();
        AddFundsResult result = new AddFundsResult();
        result.setFromAccount(account);
        return result;
    }

    public SubtractFundsResult subtractFunds(SubtractFundsRequest request) throws AccountNotFoundException, ImproperBalanceException {
        repository.subtractFundsByUsername(request.getUsername(), request.getWithdrawalAmount());
        Account account = repository.findById(request.getUsername()).get();
        SubtractFundsResult result = new SubtractFundsResult();
        result.setFromAccount(account);
        return result;
    }

    public ViewAllAccountsResult viewAllAccounts() {
        ViewAllAccountsResult result = new ViewAllAccountsResult();
        result.setAccounts(repository.findAll());
        return result;
    }

    public DeleteAccountResult deleteAccount(DeleteAccountRequest request) throws AccountNotFoundException {
        Account account = repository.findById(request.getDeleteUsername()).get();
        repository.deleteById(request.getDeleteUsername());
        DeleteAccountResult result = new DeleteAccountResult();
        result.setFromAccount(account);
        return result;
    }
}
