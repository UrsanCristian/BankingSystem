package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.dto.BankDTO;
import com.ursancristian.bankingsystem.entity.Bank;
import com.ursancristian.bankingsystem.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;


    public void createBank(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setName(bankDTO.name());
        bank.setAddress(bankDTO.address());
        bank.setContactNumber(bankDTO.contactNumber());
        bank.setContactEmail(bankDTO.contactEmail());

        bankRepository.save(bank);
    }
}