package com.ursancristian.bankingsystem.dto;

public record BankAccountDTO(String accountNumber, Integer bankId, Integer userId,
                             String currency) {
}
