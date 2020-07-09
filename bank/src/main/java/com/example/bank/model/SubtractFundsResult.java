package com.example.bank.model;

public class SubtractFundsResult {
    private int resultBalance;

    public int getResultBalance() {
        return resultBalance;
    }

    public void setResultBalance(int resultBalance) {
        this.resultBalance = resultBalance;
    }

    public void setFromAccount(Account account) {
        resultBalance = account.getAccountBalance();
    }
}
