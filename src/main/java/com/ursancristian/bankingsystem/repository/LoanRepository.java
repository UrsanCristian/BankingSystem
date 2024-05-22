package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Integer> {

    Loan findByBankAccountId(int accountId);

    List<Loan> findAllByBankAccountId(int accountId);

    @Query("SELECT l FROM Loan l JOIN l.bankAccount b WHERE b.bank.id = :bankId")
    List<Loan> findAllByBankId(int bankId);
}