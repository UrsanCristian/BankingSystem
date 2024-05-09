package com.ursancristian.bankingsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;

import java.time.LocalDate;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class BankUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "bank_user_id_seq")
    @SequenceGenerator(name = "bank_user_id_seq", sequenceName = "bank_user_id_seq", allocationSize = 1)
    private int id;

    private String FirstName;

    private String LastName;

    private String IdentityCardNumber;

    private String Email;

    private String PhoneNumber;

    private String Password;

    private String Address;

    private LocalDate DateOfBirth;

    private String Role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Transaction> transactionsSent;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Transaction> transactionsReceived;

}
