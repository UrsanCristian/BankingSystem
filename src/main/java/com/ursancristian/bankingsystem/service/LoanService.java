package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    private final BankService bankService;

    private final BankAccountService bankAccountService;

    public String takeLoan(int accountId, double amount) {
        bankService.subtractFromBudget(bankAccountService.getBankAccount(accountId).getBank().getId(), amount);
        bankAccountService.addBalance(accountId, amount);

        return "Loan taken successfully for user  " + bankAccountService.getOwnerNameById(accountId) + " in amount of " + amount + " RON";
    }
}
