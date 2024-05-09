package com.ursancristian.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Loan {
    @Id
    @SequenceGenerator(name = "loan_id_seq", sequenceName = "loan_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "loan_id_seq", strategy = GenerationType.IDENTITY)
    private int id;

    private double totalAmount;

    private double interestRate;

    private LocalDate startDate;

    private LocalDate endDate;

    private int duration;

    private double monthlyPayment;

    private boolean isPaid = false;

    @ManyToOne
    private BankAccount bankAccount;
}
