package com.example.bank.mapper;

import com.example.bank.entity.AccountEntity;
import com.example.bank.model.Account;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AccountMapper {
    public AccountEntity mapToAccountEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountId(UUID.randomUUID().toString());
        entity.setUsername(account.getUsername());
        entity.setName(account.getName());
        entity.setAccountBalance(account.getAccountBalance());

        return entity;
    }

    public Account mapToAccount(AccountEntity entity) {
        Account account = new Account();
        account.setUsername(entity.getUsername());
        account.setName(entity.getName());
        account.setAccountBalance(entity.getAccountBalance());

        return account;
    }
}
