package com.atm_interface.payload;



import lombok.Data;

@Data
public class AccountCreationDTO {
    private String accountNumber;
    private double initialBalance;
    private String accountType;
    private Long userId;
}
