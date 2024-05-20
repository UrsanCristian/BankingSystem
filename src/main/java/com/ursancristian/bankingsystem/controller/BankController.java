package com.ursancristian.bankingsystem.controller;

import com.ursancristian.bankingsystem.dto.BankDTO;
import com.ursancristian.bankingsystem.entity.Bank;
import com.ursancristian.bankingsystem.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/banking/banks")
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;


    @GetMapping
    public List<Bank> bankList() {
        return bankService.getAllBanks();
    }

    @GetMapping("/{bankId}")
    public Bank getBank(@PathVariable int bankId) {
        return bankService.getBank(bankId);
    }

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
