package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.entity.Transaction;
import com.ursancristian.bankingsystem.enumeration.StatusEnum;
import com.ursancristian.bankingsystem.exception.ElementNotFoundException;
import com.ursancristian.bankingsystem.exception.FundsValueException;
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


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> sentTransactions = transactionRepository.findAllBySenderAccountIdOrderByTransactionDateDesc(accountId);
        List<Transaction> receivedTransactions = transactionRepository.findAllByReceiverAccountIdOrderByTransactionDateDesc(accountId);

        sentTransactions.addAll(receivedTransactions);

        return sentTransactions;
    }

    public Transaction getTransaction(int transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transaction not found"));
    }


    public void addMoney(int accountId, double amount) {
        Transaction transaction = new Transaction();
        transaction.setTransactionType("Deposit");
        transaction.setAmount(amount);
        transaction.setReceiverAccount(bankAccountService.getBankAccount(accountId));
        transaction.setDescription("Deposit");

        bankAccountService.addBalance(accountId, amount);

        transaction.setStatus(StatusEnum.COMPLETED);

        transactionRepository.save(transaction);
    }

    public void withdrawMoney(int accountId, double amount) {
        if (bankAccountService.getBalanceById(accountId) < amount) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("Withdraw");
            transaction.setAmount(amount);
            transaction.setSenderAccount(bankAccountService.getBankAccount(accountId));
            transaction.setDescription("Withdraw");
            transaction.setStatus(StatusEnum.REJECTED);

            transactionRepository.save(transaction);

            throw new FundsValueException("Insufficient funds");
        } else {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("Withdraw");
            transaction.setAmount(amount);
            transaction.setSenderAccount(bankAccountService.getBankAccount(accountId));
            transaction.setDescription("Withdraw");

            bankAccountService.subtractBalance(accountId, amount);

            transaction.setStatus(StatusEnum.COMPLETED);

            transactionRepository.save(transaction);
        }
    }

    public void sendTransfer(int senderAccountId, int receiverAccountId, double amount, String description) {

        if (bankAccountService.getBalanceById(senderAccountId) < amount) {
            Transaction transaction = new Transaction();
            transaction.setTransactionType("Transfer");
            transaction.setAmount(amount);
            transaction.setSenderAccount(bankAccountService.getBankAccount(senderAccountId));
            transaction.setReceiverAccount(bankAccountService.getBankAccount(receiverAccountId));
            transaction.setDescription(description);

            transaction.setStatus(StatusEnum.REJECTED);

            transactionRepository.save(transaction);

            throw new FundsValueException("Insufficient funds");
        } else {
            if (bankAccountService.getBankAccount(senderAccountId).getCurrency().equals(bankAccountService.getBankAccount(receiverAccountId).getCurrency())) {

                Transaction transaction = new Transaction();
                transaction.setTransactionType("Transfer");
                transaction.setAmount(amount);
                transaction.setSenderAccount(bankAccountService.getBankAccount(senderAccountId));
                transaction.setReceiverAccount(bankAccountService.getBankAccount(receiverAccountId));
                transaction.setDescription(description);

                bankAccountService.subtractBalance(senderAccountId, amount);
                bankAccountService.addBalance(receiverAccountId, amount);

                transaction.setStatus(StatusEnum.COMPLETED);

                transactionRepository.save(transaction);
            } else {
                String senderCurrency = bankAccountService.getBankAccount(senderAccountId).getCurrency();
                String receiverCurrency = bankAccountService.getBankAccount(receiverAccountId).getCurrency();

                String currencyPair = senderCurrency + receiverCurrency;
                Double exchangeRate = exchangeRateRepository.findRateByCurrencyPair(currencyPair);

                if (exchangeRate == null) {
                    throw new ElementNotFoundException("Exchange rate not found");
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

                transaction.setStatus(StatusEnum.COMPLETED);

                transactionRepository.save(transaction);
            }
        }
    }

    public List<Transaction> getSentTransactionsByAccountId(int accountId) {
        return transactionRepository.findAllBySenderAccountIdOrderByTransactionDateDesc(accountId);
    }

    public List<Transaction> getReceivedTransactionsByAccountId(int accountId) {
        return transactionRepository.findAllByReceiverAccountIdOrderByTransactionDateDesc(accountId);
    }


}
