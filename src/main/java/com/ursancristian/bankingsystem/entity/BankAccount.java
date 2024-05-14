package com.ursancristian.bankingsystem.entity;

import com.ursancristian.bankingsystem.enumeration.CurrencyEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankAccount {
    @Id
    @SequenceGenerator(name = "bank_account_id_seq", sequenceName = "bank_account_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bank_account_id_seq", strategy = GenerationType.IDENTITY)
    private int id;

    private String accountNumber;

    private CurrencyEnum currency;

    private double balance = 0.0;

    @ManyToOne
    private BankUser owner;

    @ManyToOne
    private Bank bank;

    @OneToMany(mappedBy = "senderAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactionsSent;

    @OneToMany(mappedBy = "receiverAccount", cascade = CascadeType.ALL)
    private List<Transaction> transactionsReceived;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<Loan> loans;
}
