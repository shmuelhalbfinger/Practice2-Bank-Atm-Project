package com.example.bank.service;

import com.example.bank.entity.AccountEntity;
import com.example.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountEntity createAccount(AccountEntity entity) {
        return accountRepository.save(entity);
    }

    public AccountEntity updateAccountName(String username, String editName) {
        accountRepository.editAccountByUsername(username, editName);
        return accountRepository.findOneByUsername(username).get();
    }

    public AccountEntity viewAccount(String username) {
        return accountRepository.findOneByUsername(username).get();
    }

    public AccountEntity deposit(String username, int depositAmount) {
        accountRepository.depositByUsername(username, depositAmount);
        return accountRepository.findOneByUsername(username).get();
    }

    public AccountEntity withdraw(String username, int withdrawalAmount) {
        accountRepository.withdrawByUsername(username, withdrawalAmount);
        return accountRepository.findOneByUsername(username).get();
    }

    public List<AccountEntity> viewAllAccounts() {
        return accountRepository.findAll();
    }

    public AccountEntity deleteAccount(String username) {
        AccountEntity entity = accountRepository.findOneByUsername(username).get();
        accountRepository.delete(accountRepository.findOneByUsername(username).get());
        return entity;
    }
}
