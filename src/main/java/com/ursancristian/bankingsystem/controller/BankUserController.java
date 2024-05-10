package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.service.BankUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banking/user")
public class BankUserController {

    private final BankUserService bankUserService;

    @PostMapping("/create")
    public String createBankUser(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String identityCardNumber,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 @RequestParam String phoneNumber,
                                 @RequestParam String address,
                                 @RequestParam String dateOfBirth) {
        LocalDate dateOfBirthParsed = LocalDate.parse(dateOfBirth);
        bankUserService.createBankUser(firstName, lastName, identityCardNumber, email, password, phoneNumber, address, dateOfBirthParsed);

        return "Bank user created";
    }
}
