package com.ursancristian.bankingsystem.service;

import com.ursancristian.bankingsystem.entity.Bank;
import com.ursancristian.bankingsystem.entity.BankUser;
import com.ursancristian.bankingsystem.exception.UserNotFoundException;
import com.ursancristian.bankingsystem.repository.BankUserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankUserService {

    private final BankUserRepository bankUserRepository;


    public List<BankUser> getUsers() {
        return bankUserRepository.findAll();
    }


    public BankUser getUser(int userId) {
        return bankUserRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

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
