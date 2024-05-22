package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    @Query("SELECT b FROM Bank b JOIN b.bankAccounts ba JOIN ba.loans l WHERE l.id = :loanId")
    Optional<Bank> findBankByLoanId(int loanId);
}
