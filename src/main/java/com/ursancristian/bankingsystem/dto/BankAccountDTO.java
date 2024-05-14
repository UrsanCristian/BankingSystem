package com.ursancristian.bankingsystem.dto;

import com.ursancristian.bankingsystem.enumeration.CurrencyEnum;

public record BankAccountDTO(String accountNumber, Integer bankId, Integer userId,
                             CurrencyEnum currency) {
}
