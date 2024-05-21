package com.ursancristian.bankingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ExchangeRates {
    @Id
    private String currencyPair;

    private Double rate;
}
