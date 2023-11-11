package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.exception.BeneficiaryNotFoundException;
import com.aston.kupriyanov.bankAccountRestApi.repo.BeneficiaryRepo;
import com.aston.kupriyanov.bankAccountRestApi.util.LogHelper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BeneficiaryService {

    private static final Logger log = LoggerFactory.getLogger(BeneficiaryService.class);

    private final BeneficiaryRepo beneficiaryRepo;

    public Beneficiary getBeneficiaryByName(String name){
        Beneficiary beneficiary = beneficiaryRepo.getBeneficiaryByName(name);
        if (beneficiary == null){
            BeneficiaryNotFoundException e = new BeneficiaryNotFoundException(
                    String.format("Beneficiary name is %s not found", name));
            log.info(LogHelper.getLogString(this.getClass(), "Error: " + e.getMessage()));
            throw e;
        }
        return beneficiary;
    }

    public void save(Beneficiary beneficiary){
        beneficiaryRepo.save(beneficiary);
    }
}
