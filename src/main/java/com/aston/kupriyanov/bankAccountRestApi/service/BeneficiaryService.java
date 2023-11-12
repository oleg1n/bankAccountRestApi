package com.aston.kupriyanov.bankAccountRestApi.service;

import com.aston.kupriyanov.bankAccountRestApi.entity.Beneficiary;
import com.aston.kupriyanov.bankAccountRestApi.exception.PincodeIsNotValidException;
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
        return beneficiaryRepo.getBeneficiaryByName(name);
    }

    public void save(Beneficiary beneficiary){
        beneficiaryRepo.save(beneficiary);
    }

    public boolean checkPincodeOnValidation(Beneficiary beneficiary, String pincode){
        if (!beneficiary.getPinCode().equals(pincode)){
            PincodeIsNotValidException e = new PincodeIsNotValidException("Pincode is not valid. Try again!");
            log.info(LogHelper.getLogString(this.getClass(), "Error: " + e.getMessage()));
            throw e;
        }
        return true;
    }
}
