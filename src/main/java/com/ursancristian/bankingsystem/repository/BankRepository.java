package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
