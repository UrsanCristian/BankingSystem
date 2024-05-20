package com.ursancristian.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    private String Role="USER";

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BankAccount> bankAccounts;

}
