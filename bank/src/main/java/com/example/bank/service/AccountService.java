package com.example.bank.service;

import com.example.bank.entity.AccountEntity;
import com.example.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity createAccount(AccountEntity entity) {
        return accountRepository.save(entity);
    }

    public AccountEntity updateAccountName(String username, String editName) {
        accountRepository.editAccountByUsername(username, editName);
        return accountRepository.findById(username).get();
    }

    public AccountEntity viewAccount(String username) {
        return accountRepository.findById(username).get();
    }

    public AccountEntity deposit(String username, int depositAmount) {
        accountRepository.depositByUsername(username, depositAmount);
        return accountRepository.findById(username).get();
    }

    public AccountEntity withdraw(String username, int withdrawalAmount) {
        accountRepository.withdrawByUsername(username, withdrawalAmount);
        return accountRepository.findById(username).get();
    }

    public List<AccountEntity> viewAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity deleteAccount(String username) {
        AccountEntity entity = accountRepository.findById(username).get();
        accountRepository.deleteById(username);
        return entity;
    }
}
