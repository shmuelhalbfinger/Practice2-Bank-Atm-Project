package com.example.bank.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class Account {
    private String username;
    private String name;
    private int accountBalance;
}
