package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser, Integer> {
}