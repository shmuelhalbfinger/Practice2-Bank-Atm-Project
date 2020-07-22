package com.example.atm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class AccountViewer {
    private String username;
    private String name;
    private int accountBalance;
}
