package com.example.bank.model;

public class CreateAccountResult {
    private String username;
    private String name;
    private int accountBalance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setFromAccount(Account account) {
        username = account.getUsername();
        name = getName();
        accountBalance = account.getAccountBalance();
    }
}
