package com.atm_interface.controller;

import com.atm_interface.payload.AccountCreationDTO;
import com.atm_interface.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAccount(@RequestBody AccountCreationDTO accountDTO) {
        Long accountId = accountService.createAccount(accountDTO);
        return ResponseEntity.ok("Account created successfully with ID: " + accountId);
    }
    @PutMapping("/{accountId}/balance")
    public ResponseEntity<String> updateAccountBalanceById(@PathVariable Long accountId, @RequestParam double newBalance) {
        accountService.updateAccountBalanceById(accountId, newBalance);
        return ResponseEntity.ok("Account balance updated successfully");
    }

    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getAccountBalance(@PathVariable Long accountId) {
        double balance = accountService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawAmount(@PathVariable Long accountId, @RequestBody Map<String, Double> requestBody) {
        Double amount = requestBody.get("amount");
        accountService.withdrawAmount(accountId, amount);
        return ResponseEntity.ok("Amount withdrawn successfully");
    }


    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> depositAmount(@PathVariable Long accountId, @RequestBody Map<String, Double> requestBody) {
        Double amount = requestBody.get("amount");
        accountService.depositAmount(accountId, amount);
        return ResponseEntity.ok("Amount deposited successfully");
    }


    @PostMapping("/{fromAccountId}/transfer")
    public ResponseEntity<String> transferAmount(@PathVariable Long fromAccountId, @RequestBody Map<String, Object> requestBody) {
        Long toAccountId = Long.parseLong(requestBody.get("toAccountId").toString());
        Double amount = Double.parseDouble(requestBody.get("amount").toString());
        accountService.transferAmount(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok("Amount transferred successfully");
    }


}

