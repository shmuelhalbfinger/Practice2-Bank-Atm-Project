package com.example.bank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

