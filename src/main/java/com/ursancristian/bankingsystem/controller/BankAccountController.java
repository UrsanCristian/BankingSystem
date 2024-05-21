package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.dto.BankAccountDTO;
import com.ursancristian.bankingsystem.entity.BankAccount;
import com.ursancristian.bankingsystem.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banking/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> allBankAccounts() {
        return bankAccountService.getAllBankAccounts();
    }

    @GetMapping("/bank/{bank_id}")
    public List<BankAccount> allBankAccountsByBankId(@PathVariable int bank_id) {
        return bankAccountService.getBankAccountsByBankId(bank_id);
    }

    @GetMapping("/user/{user_id}")
    public List<BankAccount> bankAccountsByUserId(@PathVariable int user_id) {
        return bankAccountService.getBankAccountsByUserId(user_id);
    }

    @GetMapping("/{account_id}")
    public BankAccount bankAccount(@PathVariable int account_id) {
        return bankAccountService.getBankAccount(account_id);
    }

    @GetMapping("/{account_id}/balance")
    public Double balance(@PathVariable int account_id) {
        return bankAccountService.getBalanceById(account_id);
    }

    @GetMapping("/{account_id}/account_number")
    public String accountNumber(@PathVariable int account_id) {
        return bankAccountService.getAccountNumberById(account_id);
    }

    @PostMapping("/{user_id}/create")
    public String createBankAccount(@PathVariable int user_id,
                                    @RequestParam String currency,
                                    @RequestParam int bank_id) {

        String ShouldBeGeneratedAccountNumber = "RO2154821854318";
        Double startBalance = 0.00;

        BankAccountDTO bankAccountDTO = new BankAccountDTO(ShouldBeGeneratedAccountNumber, bank_id, user_id, currency);
        bankAccountService.createBankAccount(bankAccountDTO);

        return "Bank account created for user with id: " + user_id;
    }
}
