package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, String> {
    @Query("SELECT COUNT(*) > 0 FROM account WHERE Username = ?1")
    public boolean existsByUsername(String Username);

    @Query("SELECT AccountBalance-?2 > 0 FROM account WHERE username = ?1")
    public boolean sufficientFundsByUsername(String username, int withdrawalAmount);

    @Query("INSERT INTO account (Username, Name, AccountBalance) VALUES (?1, ?2, ?3)")
    public void createAccount(String username, String name, int initialAccountBalance);

    @Query("UPDATE account SET Name = ?2 WHERE Username = ?1")
    public void editAccountByUsername(String username, String name);

    @Query("UPDATE account SET AccountBalance = AccountBalance + ?2 WHERE Username = ?1")
    public void addFundsByUsername(String username, int depositAmount);

    @Query("UPDATE account SET AccountBalance = AccountBalance - ?2 WHERE Username = ?1")
    public void subtractFundsByUsername(String username, int withdrawalAmount);
}
