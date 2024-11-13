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
        try {
            if (accountRepository.existsById(id)) {
                Account account = accountRepository.updateAccount(id, accountRequestDTO.getDateHigh(), accountRequestDTO.getBalance());
                return new AccountResponseDTO(id, account.getDateHigh(), account.getBalance(), account.getAnnulled());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    // CRUD - DELETE
    public AccountResponseDTO deleteById(Long id) throws Exception {
        try {
            if (accountRepository.existsById(id)) {
                Account accountDelete = accountRepository.findById(id).get();
                accountRepository.deleteById(id);
                return new AccountResponseDTO(accountDelete.getId_account(), accountDelete.getDateHigh(), accountDelete.getBalance(), accountDelete.getAnnulled());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    // Cargar saldo en cuenta
    public AccountResponseDTO loadBalance(Long accountId, AccountRequestDTO accountRequestDTO) throws Exception {
        try {
            if (accountRepository.existsById(accountId)) {
                Account accountModify = accountRepository.updateBalance(accountId, accountRequestDTO.getBalance());
                return new AccountResponseDTO(accountModify.getId_account(), accountModify.getDateHigh(), accountModify.getBalance(), accountModify.getAnnulled());
            }
        } catch (Exception e) {
            throw new Exception("User or Account not found");
        }
        return null;
    }

    // Descontar saldo en cuenta
    public AccountResponseDTO discountBalance(Long accountId, AccountRequestDTO accountRequestDTO) throws Exception {
        Account account = accountRepository.findById(accountId).orElse(null);
        try {
            if (account != null && account.getBalance() >= accountRequestDTO.getBalance()) {
                Account accountModify = accountRepository.updateBalance(accountId, accountRequestDTO.getBalance());
                return new AccountResponseDTO(accountModify.getId_account(), accountModify.getDateHigh(), accountModify.getBalance(), accountModify.getAnnulled());
            }
        } catch (Exception e) {
            throw new Exception("Account not found");
        }
        return null;
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
}