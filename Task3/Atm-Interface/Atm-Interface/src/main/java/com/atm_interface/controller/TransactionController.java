package com.atm_interface.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.atm_interface.entity.Transaction;
import com.atm_interface.service.TransactionService;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccountId(@PathVariable Long accountId) {
        List<Transaction> transactions = transactionService.getAllTransactionsByAccountId(accountId);
        return ResponseEntity.ok(transactions);
    }
}
