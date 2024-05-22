package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.entity.Loan;
import com.ursancristian.bankingsystem.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banking/loans")
public class LoanController {

    private final LoanService loanService;

    @GetMapping("/all")
    public List<Loan> AllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/bank/{bankId}")
    public List<Loan> LoansByBankId(@PathVariable int bankId) {
        return loanService.getAllLoansByBankId(bankId);
    }

    @GetMapping("/{loanId}")
    public Loan LoanDetails(@PathVariable int loanId) {
        return loanService.checkLoanDetailsByLoanId(loanId);
    }

    @GetMapping("/account/{accountId}")
    public List<Loan> LoansByAccountId(@PathVariable int accountId) {
        return loanService.checkLoansListByAccountId(accountId);
    }

    @PostMapping()
    public String TakeLoan(@RequestParam int accountId,
                           @RequestParam double amount,
                           @RequestParam double interestRate,
                           @RequestParam int months) {
        return loanService.takeLoan(accountId, amount, interestRate, months);
    }

    @GetMapping("/pay/{loanId}")
    public String PayLoan(@PathVariable int loanId) {
        return loanService.payMonthlyLoan(loanId);
    }
}
