package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.dto.BankDTO;
import com.ursancristian.bankingsystem.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banking/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @PostMapping("/create")
    public String createBank(@RequestParam String name,
                             @RequestParam String address,
                             @RequestParam String phoneNumber,
                             @RequestParam String email) {

        BankDTO bankDTO = new BankDTO(name, address, phoneNumber, email);

        bankService.createBank(bankDTO);

        return "Bank created";
    }
}
