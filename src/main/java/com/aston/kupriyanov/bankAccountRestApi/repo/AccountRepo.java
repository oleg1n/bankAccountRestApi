package com.aston.kupriyanov.bankAccountRestApi.repo;

import com.aston.kupriyanov.bankAccountRestApi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface AccountRepo extends JpaRepository<Account, UUID> {
}
