package com.example.bank_account_service.web;

import com.example.bank_account_service.dto.BankAccountRequestDTO;
import com.example.bank_account_service.dto.BankAccountResponseDTO;
import com.example.bank_account_service.entites.BankAccount;
import com.example.bank_account_service.entites.Customer;
import com.example.bank_account_service.repositories.BankAccountRepository;
import com.example.bank_account_service.repositories.CustomerRepository;
import com.example.bank_account_service.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class BankAccountGraphQLController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;

    @QueryMapping // quand on une requette de type query == on va executer cette méthode
    public List<BankAccount> accountsList(){
        return bankAccountRepository.findAll();
    }

    @QueryMapping // quand on une requette de type query == on va executer cette méthode
    public BankAccount bankAccountById(@Argument String id){
        return bankAccountRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String.format("Bank account with id %s not found", id)));
    }

    @MutationMapping
    public BankAccountResponseDTO addAccount(@Argument BankAccountRequestDTO bankAccount){
        return accountService.addAccount(bankAccount);
    }

    /**@MutationMapping
    public BankAccount addAccount(@Argument BankAccountDTO bankAccount){
    return bankAccountRepository.save(bankAccount);
    }**/

    @MutationMapping
    public BankAccountResponseDTO updateAccount(@Argument String id, @Argument BankAccountRequestDTO bankAccount){
        return accountService.updateAccount(id, bankAccount);
    }

    @MutationMapping
    public Boolean deleteAccount(@Argument String id){
        bankAccountRepository.deleteById(id);
        return true;
    }

    @QueryMapping
    public List<Customer> customers(){
        return customerRepository.findAll();
    }

}

/***@Data @AllArgsConstructor @NoArgsConstructor
class BankAccountDTO{
private String type;
private Double balance;
private String currency;
}**/
// au lieu de ^^ on peut creer:
/** record BankAccountDTO(Double balance, String type, String currency){
}**/