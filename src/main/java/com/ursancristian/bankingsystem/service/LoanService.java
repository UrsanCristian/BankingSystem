package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.entity.Bank;
import com.ursancristian.bankingsystem.entity.Loan;
import com.ursancristian.bankingsystem.exception.ElementNotFoundException;
import com.ursancristian.bankingsystem.repository.BankRepository;
import com.ursancristian.bankingsystem.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final LoanRepository loanRepository;

    private final BankRepository bankRepository;

    private final BankService bankService;

    private final BankAccountService bankAccountService;

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Loan> getAllLoansByBankId(int bankId) {
        return loanRepository.findAllByBankId(bankId);
    }

    public String takeLoan(int accountId, double amount, double interestRate, int months) {

        Loan loan = new Loan();
        loan.setBankAccount(bankAccountService.getBankAccount(accountId));
        loan.setTotalAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(months));

        double monthlyInterestRate = interestRate / 12 / 100;
        double monthlyPayment = amount * monthlyInterestRate / (1 - Math.pow(1 + monthlyInterestRate, -months));

        loan.setMonthlyPayment(monthlyPayment);


        bankService.subtractFromBudget(bankAccountService.getBankAccount(accountId).getBank().getId(), amount);
        bankAccountService.addBalance(accountId, amount);

        loanRepository.save(loan);

        return "Loan taken successfully for user  " + bankAccountService.getOwnerNameById(accountId) + " in amount of " + amount + bankAccountService.getBankAccount(accountId).getCurrency();
    }

    public Loan checkLoanDetailsByLoanId(int loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);

        if (loan == null) {
            throw new ElementNotFoundException("Loan not found");
        } else {
            return loan;
        }
    }

    public List<Loan> checkLoansListByAccountId(int accountId) {

        List<Loan> loans = loanRepository.findAllByBankAccountId(accountId);

        if (loans.isEmpty()) {
            throw new ElementNotFoundException("No loans found for this account");
        } else {
            return loans;
        }
    }

    public String payMonthlyLoan(int loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new ElementNotFoundException("Loan not found"));
        Bank bank = bankRepository.findBankByLoanId(loanId).orElseThrow(() -> new ElementNotFoundException("Bank not found"));

        if (!loan.isPaid()) {
            double monthPayment = loan.getMonthlyPayment();

            if (bankAccountService.getBalanceById(loan.getBankAccount().getId()) < monthPayment) {
                throw new ElementNotFoundException("Insufficient funds");
            } else {
                bankAccountService.subtractBalance(loan.getBankAccount().getId(), monthPayment);
                loan.setPayedAmount(loan.getPayedAmount() + monthPayment);
                bank.setBudget(bank.getBudget() + monthPayment);
                loanRepository.save(loan);

                if (loan.getPayedAmount() >= loan.getTotalAmount()) {
                    loan.setPaid(true);
                    loanRepository.save(loan);
                    return "Loan has been paid in full";
                }
                return "Monthly payment of " + monthPayment + " " + loan.getBankAccount().getCurrency() + " has been made for loan " + loan.getId();
            }
        } else {
            return "Loan has already been paid";
        }
    }
}
