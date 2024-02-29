package com.atm_interface.service;

import com.atm_interface.entity.Account;
import com.atm_interface.entity.Transaction;
import com.atm_interface.entity.User;
import com.atm_interface.payload.AccountCreationDTO;
import com.atm_interface.repository.AccountRepository;
import com.atm_interface.repository.TransactionRepository;
import com.atm_interface.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;


@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public Long createAccount(AccountCreationDTO accountDTO) {
        Account account = new Account();
        account.setAccountNumber(accountDTO.getAccountNumber());
        account.setBalance(accountDTO.getInitialBalance());
        account.setAccountType(accountDTO.getAccountType());

        User user = userRepository.findById(accountDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        account.setUser(user);

        Account savedAccount = accountRepository.save(account);
        return savedAccount.getId();
    }

    @Transactional
    public void updateAccountBalanceById(Long accountId, double newBalance) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(newBalance);
        accountRepository.save(account);
    }

    public double getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance();
    }

    public void withdrawAmount(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(currentBalance - amount);
        accountRepository.save(account);

        recordTransaction(accountId, amount, "Withdrawal");
    }

    public void depositAmount(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double currentBalance = account.getBalance();
        account.setBalance(currentBalance + amount);
        accountRepository.save(account);

        recordTransaction(accountId, amount, "Deposit");
    }

    public void transferAmount(Long fromAccountId, Long toAccountId, double amount) {
        withdrawAmount(fromAccountId, amount);
        depositAmount(toAccountId, amount);

        recordTransaction(fromAccountId, amount, "Transfer to " + toAccountId);
        recordTransaction(toAccountId, amount, "Transfer from " + fromAccountId);
    }

    private void recordTransaction(Long accountId, double amount, String description) {
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionDate(new Date());
        transactionRepository.save(transaction);
    }
}
