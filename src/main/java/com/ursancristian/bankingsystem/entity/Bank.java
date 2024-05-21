package com.ursancristian.bankingsystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Bank {

    @Id
    @SequenceGenerator(name = "bank_id_seq", sequenceName = "bank_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bank_id_seq", strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String address;

    private String contactNumber;

    private String contactEmail;

    private Double budget = 1000000.0;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<BankAccount> bankAccounts;
}
