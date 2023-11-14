package com.aston.kupriyanov.bankAccountRestApi.repo;

import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, UUID> {

    public Beneficiary getBeneficiaryByName(String name);
}
