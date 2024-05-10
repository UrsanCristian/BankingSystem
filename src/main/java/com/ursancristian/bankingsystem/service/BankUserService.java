package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.entity.BankUser;
import com.ursancristian.bankingsystem.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BankUserService {

    private final BankUserRepository bankUserRepository;

    public void createBankUser(String firstName,
                               String lastName,
                               String identityCardNumber,
                               String email,
                               String password,
                               String phoneNumber,
                               String address,
                               LocalDate dateOfBirth) {

        BankUser bankUser = new BankUser();
        bankUser.setFirstName(firstName);
        bankUser.setLastName(lastName);
        bankUser.setIdentityCardNumber(identityCardNumber);
        bankUser.setEmail(email);
        bankUser.setPassword(password);
        bankUser.setPhoneNumber(phoneNumber);
        bankUser.setAddress(address);
        bankUser.setDateOfBirth(dateOfBirth);

        bankUserRepository.save(bankUser);
    }
}
