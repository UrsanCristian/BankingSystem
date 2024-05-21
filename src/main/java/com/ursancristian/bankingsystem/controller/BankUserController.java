package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.entity.BankUser;
import com.ursancristian.bankingsystem.service.BankUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/banking/users")
public class BankUserController {

    private final BankUserService bankUserService;

    @GetMapping
    public List<BankUser> bankUserList() {
        return bankUserService.getUsers();

    }

    @GetMapping("/{userId}")
    public BankUser bankUserDetails(@PathVariable int userId) {
        return bankUserService.getUser(userId);
    }


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
