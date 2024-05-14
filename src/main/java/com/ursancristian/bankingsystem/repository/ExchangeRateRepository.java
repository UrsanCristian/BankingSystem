package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, String>{

    Double findRateByCurrencyPair(String currencyPair);
}
