package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
}