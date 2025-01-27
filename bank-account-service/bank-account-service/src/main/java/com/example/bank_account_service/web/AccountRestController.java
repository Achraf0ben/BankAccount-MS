package com.example.bank_account_service.web;

import com.example.bank_account_service.dto.BankAccountRequestDTO;
import com.example.bank_account_service.dto.BankAccountResponseDTO;
import com.example.bank_account_service.entites.BankAccount;
import com.example.bank_account_service.mappers.AccountMapper;
import com.example.bank_account_service.repositories.BankAccountRepository;
import com.example.bank_account_service.service.AccountService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

//http://localhost:8081/swagger-ui/index.html#/account-rest-controller/save

@RestController
@RequestMapping("/api")
public class AccountRestController {

    private BankAccountRepository bankAccountRepository;
    private AccountService accountService;
    private AccountMapper accountMapper;

    public AccountRestController(AccountMapper accountMapper, AccountService accountService, BankAccountRepository bankAccountRepository) {
        this.accountMapper = accountMapper;
        this.accountService = accountService;
        this.bankAccountRepository = bankAccountRepository;
    }

    @GetMapping("/bankAccounts")
    public List<BankAccount> getBankAccounts() {

        return bankAccountRepository.findAll();
    }

    @GetMapping("/bankAccounts/{id}") //@PathVariable= param qui va etre recuperé à partir du path
    public BankAccount getBankAccount(@PathVariable String id) {
        return bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));

    }

    //@PostMapping("/bankAccounts")
    /**public BankAccount save(@RequestBody BankAccount bankAccount) {
        if(bankAccount.getId()==null) bankAccount.setId(UUID.randomUUID().toString());
        return bankAccountRepository.save(bankAccount);
    }**/
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO RequestDTO) {

        return accountService.addAccount(RequestDTO);
    }


    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account=bankAccountRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Account %s not found", id)));
        if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt()!=null) account.setCreatedAt(new Date());
        if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
        if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }

    @DeleteMapping("/bankAccounts/{id}") //@PathVariable= param qui va etre recuperé à partir du path
    public void deleteAccount(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
