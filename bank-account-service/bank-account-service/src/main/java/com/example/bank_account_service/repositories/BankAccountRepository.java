package com.example.bank_account_service.repositories;

import com.example.bank_account_service.entites.BankAccount;
import com.example.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource //pour ne pas creer le controller pour gerer bancaccount
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {

    @RestResource(path = "/byType")
    //pour ne pas utiliser findby mais bytype et t
    //= http://localhost:8081/bankAccounts/search/findByType?type=CURRENT_ACCOUNT
    //=http://localhost:8081/bankAccounts/search/ByType?t=CURRENT_ACCOUNT
    List<BankAccount> findByType(@Param("t") AccountType type);

}
