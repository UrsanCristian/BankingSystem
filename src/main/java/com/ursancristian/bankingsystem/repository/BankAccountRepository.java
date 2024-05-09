package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}