package org.microservice_user.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.microservice_user.Dtos.AccountRequestDTO;
import org.microservice_user.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    // CRUD - READ - GET
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todas las cuentas.\"}");
        }
    }

    // CRUD - CREATE - POST
    @PostMapping("")
    public ResponseEntity<?> addAccount(@RequestBody @Valid AccountRequestDTO account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.addAccount(account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    // CRUD - UPDATE - PUT
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody @Valid AccountRequestDTO account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(id, account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    // CRUD - DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo borrar, intente nuevamente más tarde.\"}");
        }
    }

    // Admin anular Cuenta - Se necesita el ID de la cuenta a anular y un boolean ( true - false )
    @PutMapping("/annulledAccount/{id_account}/{annulled}")
    public ResponseEntity<?> annulledAccount(@PathVariable Long id_account, @PathVariable Boolean annulled) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.annulledAccount(id_account, annulled));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo anular la cuenta, intente nuevamente.\"}");
        }
    }

    // Cargar Saldo en cuenta - Se necesita el ID de la Account a cargar y el monto ( balance ).
    @PostMapping("/loadBalance/account/{id_account}/load/{balance}")
    public ResponseEntity<?> loadBalance(@PathVariable Long id_account, @PathVariable double balance){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.loadBalance(id_account, balance));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo agregar el saldo a la cuenta, intente nuevamente más tarde.\"}");
        }
    }

    // Descontar Saldo en cuenta - Se necesita el ID de la Account a descontar y el monto ( discount ).
    @PostMapping("/discountBalance/account/{id_account}/discount/{discount}")
    public ResponseEntity<?> discountBalance(@PathVariable Long id_account, @PathVariable Long discount){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.discountBalance(id_account, discount));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al intentar descontar el saldo de la cuenta, por favor intente nuevamente.\"}");
        }
    }
}