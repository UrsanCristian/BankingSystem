package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.dto.BankDTO;
import com.ursancristian.bankingsystem.entity.Bank;
import com.ursancristian.bankingsystem.exception.ElementNotFoundException;
import com.ursancristian.bankingsystem.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Bank getBank(int bankId) {
        Bank bank = bankRepository.findById(bankId).orElse(null);

        if (bank == null) {
            throw new ElementNotFoundException("Bank not found");
        } else {
            return bank;
        }

    }

    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public void subtractFromBudget(int bankId, double amount) {
        Bank bank = bankRepository.findById(bankId).orElse(null);
        if (bank == null) {
            throw new ElementNotFoundException("Bank not found");
        } else {
            bank.setBudget(bank.getBudget() - amount);
        }
    }
}
