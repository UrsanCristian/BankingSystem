package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.entity.Transaction;
import com.ursancristian.bankingsystem.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banking/transactions")
public class TransactionController {

    private final TransactionService transactionService;


    @GetMapping("/all")
    public List<Transaction> allTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("account/{accountId}")
    public List<Transaction> transactionsByAccountId(@PathVariable("accountId") int accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }

    @GetMapping("/{transactionId}")
    public Transaction transaction(@PathVariable int transactionId) {
        try {
            return transactionService.getTransaction(transactionId);
        } catch (RuntimeException err) {
            throw new RuntimeException(err.getMessage());
        }
    }

    @GetMapping("/sent")
    public List<Transaction> sentTransactions(@RequestParam int accountId) {
        return transactionService.getSentTransactionsByAccountId(accountId);
    }

    @GetMapping("/received")
    public List<Transaction> receivedTransactions(@RequestParam int accountId) {
        return transactionService.getReceivedTransactionsByAccountId(accountId);
    }

    @PostMapping("/add")
    public String addMoney(@RequestParam int accountId, @RequestParam double amount) {
        transactionService.addMoney(accountId, amount);
        return "Deposit successful";
    }

    @PostMapping("/withdraw")
    public String withdrawMoney(@RequestParam int accountId, @RequestParam double amount) {
        try {
            transactionService.withdrawMoney(accountId, amount);
            return "Withdraw successful";
        } catch (RuntimeException err) {
            return err.getMessage();
        }
    }

    @PostMapping("/send")
    public String sendTransfer(@RequestParam int senderAccountId,
                               @RequestParam int receiverAccountId,
                               @RequestParam double amount,
                               @RequestParam String description) {

        try {
            transactionService.sendTransfer(senderAccountId, receiverAccountId, amount, description);
            return "Transfer successful";
        } catch (RuntimeException err) {
            return err.getMessage();
        }
    }
}
