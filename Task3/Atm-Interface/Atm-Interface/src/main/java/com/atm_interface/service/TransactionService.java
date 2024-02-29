package com.atm_interface.service;

import com.atm_interface.payload.TransactionType;
import org.springframework.stereotype.Service;
import com.atm_interface.entity.Transaction;
import com.atm_interface.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    public Transaction saveTransaction(Long accountId, double amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setTransactionDate(new Date());
        transaction.setAmount(amount);


        return transactionRepository.save(transaction);
    }
}


