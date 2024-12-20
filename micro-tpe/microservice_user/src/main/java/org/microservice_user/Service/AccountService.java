package org.microservice_user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.microservice_user.Dtos.AccountRequestDTO;
import org.microservice_user.Dtos.AccountResponseDTO;
import org.microservice_user.Entities.Account;
import org.microservice_user.Repository.AccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    // CRUD - READ - GET
    public List<AccountResponseDTO> findAll() throws Exception {
        try {
            List<Account> accounts = accountRepository.findAll();
            List<AccountResponseDTO> accountsDTOS = new ArrayList<>();
            for (Account account : accounts) {
                AccountResponseDTO accountResponseDTO = new AccountResponseDTO(account.getId_account(), account.getDateHigh(), account.getBalance(), account.getAnnulled());
                accountsDTOS.add(accountResponseDTO);
            }
            return accountsDTOS;
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // CRUD - CREATE - POST
    public AccountResponseDTO addAccount(AccountRequestDTO accountRequestDTO) throws Exception {
        Account account = mapToAccount(accountRequestDTO);
        try{
            accountRepository.save(account);
            return new AccountResponseDTO(account.getId_account(), account.getDateHigh(), account.getBalance(), account.getAnnulled(), account.getUsers());
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private Account mapToAccount(AccountRequestDTO accountRequestDTO) throws Exception {
        Account account = new Account();
        account.setDateHigh(accountRequestDTO.getDateHigh());
        account.setBalance(accountRequestDTO.getBalance());
        return account;
    }

    // CRUD - UPDATE - PUT
    public AccountResponseDTO updateAccount(Long id, AccountRequestDTO accountRequestDTO) throws Exception {
        accountRepository.updateAccount(id, accountRequestDTO.getDateHigh(), accountRequestDTO.getBalance());
        Account account = accountRepository.findById(id).get();
        return new AccountResponseDTO(id, account.getDateHigh(), account.getBalance(), account.getAnnulled());
    }

    // CRUD - DELETE
    public AccountResponseDTO deleteById(Long id) throws Exception {
        Account accountDelete = accountRepository.findById(id).get();
        accountRepository.deleteById(id);
        return new AccountResponseDTO(accountDelete.getId_account(), accountDelete.getDateHigh(), accountDelete.getBalance(), accountDelete.getAnnulled());
    }

    // Cargar saldo en cuenta
    public AccountResponseDTO loadBalance(Long accountId, Double balance) throws Exception {
        Account account = accountRepository.findById(accountId).get();
        Double updateBalance = account.getBalance() + balance;
        accountRepository.updateBalance(accountId, updateBalance);
        return new AccountResponseDTO(account.getId_account(), account.getDateHigh(), account.getBalance() + balance, account.getAnnulled());
    }

    // Descontar saldo en cuenta
    public AccountResponseDTO discountBalance(Long accountId, Double balance) throws Exception {
        Account account = accountRepository.findById(accountId).get();
        if (account.getBalance() >= balance) {
            Double updateBalance = account.getBalance() - balance;
            accountRepository.updateBalance(accountId, updateBalance);
            return new AccountResponseDTO(accountId, account.getDateHigh(), account.getBalance() - balance, account.getAnnulled());
        }
        else{
            throw new Exception("No tienes suficiente saldo");
        }
    }

    // Anular cuenta ( Req de admin )
    public AccountResponseDTO annulledAccount(Long id, Boolean annulled) throws Exception {
        try {
            if (accountRepository.existsById(id)) {
                accountRepository.updateAnnulledAccount(id, annulled);
                Account account = accountRepository.findById(id).get();
                return new AccountResponseDTO(id, account.getDateHigh(), account.getBalance(), account.getAnnulled());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    public AccountResponseDTO getLastAccount(){
        Account account = accountRepository.getLastAccount();
        return new AccountResponseDTO(account.getId_account(), account.getDateHigh(), account.getBalance(), account.getAnnulled());
    }
}