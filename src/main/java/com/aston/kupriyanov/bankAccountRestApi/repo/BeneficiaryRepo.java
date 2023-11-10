package com.aston.kupriyanov.bankAccountRestApi.repo;

import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, UUID> {
    @Query(
            value = "SELECT * FROM beneficiary b WHERE b.name = ?",
            nativeQuery = true)
    public Beneficiary getBeneficiaryByName(String name);
}
