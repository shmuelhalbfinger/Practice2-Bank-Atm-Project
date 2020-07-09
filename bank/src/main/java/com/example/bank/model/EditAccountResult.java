package com.example.bank.model;

public class EditAccountResult {
    private String editName;

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public void setFromAccount(Account account) {
        editName = account.getName();
    }
}
