package com.aston.kupriyanov.bankAccountRestApi.repo;

import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountRepo extends JpaRepository<Account, UUID> {
    @Query(
            value = "SELECT * FROM account a WHERE a.number = ?",
            nativeQuery = true)
    public Account getAccountByNumber(String number);
}
