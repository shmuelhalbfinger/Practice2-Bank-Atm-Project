package com.example.bank.model;

import java.util.List;

public class ViewAllAccountsResult {
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
