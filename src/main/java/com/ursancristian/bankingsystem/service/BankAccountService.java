package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.dto.BankAccountDTO;
import com.ursancristian.bankingsystem.entity.BankAccount;
import com.ursancristian.bankingsystem.entity.BankUser;
import com.ursancristian.bankingsystem.repository.BankAccountRepository;
import com.ursancristian.bankingsystem.repository.BankRepository;
import com.ursancristian.bankingsystem.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;
    private final BankRepository bankRepository;
    private final BankUserRepository bankUserRepository;

    public List<BankAccount> getBankAccountsByUserId(int userId) {
        BankUser user = bankUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return bankAccountRepository.findAllByOwnerOrderByBalanceDesc(user);
    }

    public BankAccount getBankAccount(int accountId) {
        return bankAccountRepository.findById(accountId).orElse(null);
    }

    public List<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public void createBankAccount(BankAccountDTO bankAccountDTO) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(bankAccountDTO.accountNumber());
        bankAccount.setCurrency(bankAccountDTO.currency());
        bankAccount.setBank(bankRepository.findById(bankAccountDTO.bankId()).orElse(null));
        bankAccount.setOwner(bankUserRepository.findById(bankAccountDTO.userId()).orElse(null));

        bankAccountRepository.save(bankAccount);
    }

    public void deleteBankAccount(int accountId) {
        bankAccountRepository.deleteById(accountId);
    }

    public void addBalance(int accountId, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found");
        } else {
            bankAccount.setBalance(bankAccount.getBalance() + amount);
        }
    }

    public void subtractBalance(int accountId, double amount) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found");
        } else {
            bankAccount.setBalance(bankAccount.getBalance() - amount);
        }
    }

    public Double getBalanceById(int accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found");
        } else {
            return bankAccount.getBalance();
        }
    }

    public String getAccountNumberById(int accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found");
        } else {
            return bankAccount.getAccountNumber();
        }
    }

    public String getOwnerNameById(int accountId) {
        BankAccount bankAccount = bankAccountRepository.findById(accountId).orElse(null);
        if (bankAccount == null) {
            throw new RuntimeException("Bank account not found");
        } else {
            return bankAccount.getOwner().getFirstName() + " " + bankAccount.getOwner().getLastName();
        }
    }
}