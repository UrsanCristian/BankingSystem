package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}