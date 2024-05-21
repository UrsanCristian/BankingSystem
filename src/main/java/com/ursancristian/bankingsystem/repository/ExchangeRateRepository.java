package com.ursancristian.bankingsystem.repository;

import com.ursancristian.bankingsystem.entity.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRates, String> {

    @Query("SELECT e.rate FROM ExchangeRates e WHERE e.currencyPair = :currencyPair")
    Double findRateByCurrencyPair(String currencyPair);
}
