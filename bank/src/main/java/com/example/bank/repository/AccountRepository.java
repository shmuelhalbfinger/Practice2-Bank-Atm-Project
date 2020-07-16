package com.example.bank.repository;

import com.example.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO account (username, name, account_balance) VALUES (:username, :name, :initialAccountBalance)", nativeQuery = true)
    public void createAccount(@Param("username") String username, @Param("name") String name, @Param("initialAccountBalance") int initialAccountBalance);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET name = :editName WHERE username = :username", nativeQuery = true)
    public void editAccountByUsername(@Param("username") String username, @Param("editName") String editName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET account_balance = account_balance + :depositAmount WHERE username = :username", nativeQuery = true)
    public void addFundsByUsername(@Param("username") String username, @Param("depositAmount") int depositAmount);

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET account_balance = account_balance - :withdrawalAmount WHERE username = :username", nativeQuery = true)
    public void subtractFundsByUsername(@Param("username") String username, @Param("withdrawalAmount") int withdrawalAmount);
}
