package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.BankAccount;
import com.ursancristian.bankingsystem.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {

    List<BankAccount> findAllByOwnerOrderByBalanceDesc(BankUser owner);
}