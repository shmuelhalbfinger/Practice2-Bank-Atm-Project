package com.example.bank.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor
@Setter
public class AccountEntity {
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "name")
    private String name;
    @Column(name = "account_balance")
    private int accountBalance;
}

