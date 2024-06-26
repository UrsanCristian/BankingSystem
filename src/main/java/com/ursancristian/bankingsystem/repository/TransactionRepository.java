package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findAllBySenderAccountIdOrderByTransactionDateDesc(int accountId);

    List<Transaction> findAllByReceiverAccountIdOrderByTransactionDateDesc(int accountId);
}