package com.example.bank_account_service.entites;

import com.example.bank_account_service.enums.AccountType;
import org.springframework.data.rest.core.config.Projection;

//Je peux demander a un service de me donner un compte avec des attributs precises

@Projection(types = BankAccount.class, name = "p1")
public interface AccountProjection {
    public String getId();
    public AccountType getType();
    public String getBalance();
}
