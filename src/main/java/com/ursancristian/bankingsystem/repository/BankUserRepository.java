package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Integer> {
}