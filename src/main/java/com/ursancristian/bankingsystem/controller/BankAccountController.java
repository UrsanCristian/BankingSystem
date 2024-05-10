package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.dto.BankAccountDTO;
import com.ursancristian.bankingsystem.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banking/account")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @GetMapping("/{user_id}")
    public String getBankAccountsByUserId(@PathVariable int user_id) {
        return "Bank accounts for user with id: " + user_id;
    }

    @PostMapping("/{user_id}/create")
    public String createBankAccount(@PathVariable int user_id, @RequestParam String currency, @RequestParam int bank_id) {

        String ShouldBeGeneratedAccountNumber = "RO2154821854318";
        Double startBalance = 0.00;

        BankAccountDTO bankAccountDTO = new BankAccountDTO(ShouldBeGeneratedAccountNumber, bank_id, user_id, currency);
        bankAccountService.createBankAccount(bankAccountDTO);

        return "Bank account created for user with id: " + user_id;
    }
}
