package com.ursancristian.bankingsystem.entity;
import com.ursancristian.bankingsystem.enumeration.StatusEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @SequenceGenerator(name = "transaction_id_seq", sequenceName = "transaction_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "transaction_id_seq", strategy = GenerationType.IDENTITY)
    private int id;

    private String transactionType;

    private double amount;

    private LocalDateTime transactionDate = LocalDateTime.now();

    private String description;

    @ManyToOne
    private BankAccount senderAccount;

    @ManyToOne
    private BankAccount receiverAccount;

    private StatusEnum status;
}
