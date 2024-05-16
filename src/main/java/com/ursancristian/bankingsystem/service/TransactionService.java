package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.entity.Transaction;
import com.ursancristian.bankingsystem.repository.ExchangeRateRepository;
import com.ursancristian.bankingsystem.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final BankAccountService bankAccountService;

    private final ExchangeRateRepository exchangeRateRepository;

    public void sendTransfer(int senderAccountId, int receiverAccountId, double amount, String description) {
        if (bankAccountService.getBankAccount(senderAccountId).getCurrency() ==
                bankAccountService.getBankAccount(receiverAccountId).getCurrency()) {

            Transaction transaction = new Transaction();
            transaction.setTransactionType("Transfer");
            transaction.setAmount(amount);
            transaction.setSenderAccount(bankAccountService.getBankAccount(senderAccountId));
            transaction.setReceiverAccount(bankAccountService.getBankAccount(receiverAccountId));
            transaction.setDescription(description);

            bankAccountService.subtractBalance(senderAccountId, amount);
            bankAccountService.addBalance(receiverAccountId, amount);

            transactionRepository.save(transaction);
        } else {
            String senderCurrency = bankAccountService.getBankAccount(senderAccountId).getCurrency();
            String receiverCurrency = bankAccountService.getBankAccount(receiverAccountId).getCurrency();

            String currencyPair = senderCurrency + receiverCurrency;
            Double exchangeRate = exchangeRateRepository.findRateByCurrencyPair(currencyPair);

            if (exchangeRate == null) {
                throw new RuntimeException("Exchange rate not found");
            }

            double convertedAmount = amount * exchangeRate;

            Transaction transaction = new Transaction();
            transaction.setTransactionType("Transfer");
            transaction.setAmount(amount);
            transaction.setSenderAccount(bankAccountService.getBankAccount(senderAccountId));
            transaction.setReceiverAccount(bankAccountService.getBankAccount(receiverAccountId));
            transaction.setDescription(description);

            bankAccountService.subtractBalance(senderAccountId, amount);
            bankAccountService.addBalance(receiverAccountId, convertedAmount);

            transactionRepository.save(transaction);
        }

    }

    public List<Transaction> getSendTransactionsByAccountId(int accountId) {
        return transactionRepository.findAllBySenderAccountIdOrderByTransactionDateDesc(accountId);
    }


}
