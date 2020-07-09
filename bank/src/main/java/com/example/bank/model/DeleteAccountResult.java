package com.example.bank.model;

public class DeleteAccountResult {
    private String deletedAccountUsername;

    public String getDeletedAccountUsername() {
        return deletedAccountUsername;
    }

    public void setDeletedAccountUsername(String deletedAccountUsername) {
        this.deletedAccountUsername = deletedAccountUsername;
    }

    public void setFromAccount(Account account) {
        deletedAccountUsername = account.getUsername();
    }
}
