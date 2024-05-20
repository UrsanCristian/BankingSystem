package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.entity.Loan;
import com.ursancristian.bankingsystem.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    private final BankService bankService;

    private final BankAccountService bankAccountService;

    public String takeLoan(int accountId, double amount, double interestRate, int months) {

        Loan loan = new Loan();
        loan.setBankAccount(bankAccountService.getBankAccount(accountId));
        loan.setTotalAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(months));
        loan.setMonthlyPayment((amount + amount * interestRate) / months);

        bankService.subtractFromBudget(bankAccountService.getBankAccount(accountId).getBank().getId(), amount);
        bankAccountService.addBalance(accountId, amount);

        loanRepository.save(loan);

        return "Loan taken successfully for user  " + bankAccountService.getOwnerNameById(accountId) + " in amount of " + amount + bankAccountService.getBankAccount(accountId).getCurrency();
    }
}
