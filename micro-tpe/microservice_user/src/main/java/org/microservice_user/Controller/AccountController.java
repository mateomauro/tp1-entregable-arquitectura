package org.microservice_user.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todas las cuentas", description = "Obtiene todas las cuentas del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todas las cuentas"),
            @ApiResponse(responseCode = "404", description = "Error al obtener las cuentas")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todas las cuentas.\"}");
        }
    }

    @Operation(summary = "Agregar una nueva cuenta", description = "Crea una nueva cuenta con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    @PostMapping("")
    public ResponseEntity<?> addAccount(@RequestBody AccountRequestDTO account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.addAccount(account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Actualizar una cuenta existente", description = "Actualiza los detalles de la cuenta por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta actualizada exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de cuenta o datos inválidos")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable Long id, @RequestBody AccountRequestDTO account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(id, account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Eliminar una cuenta", description = "Elimina una cuenta por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta eliminada exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de cuenta inválido o error en la eliminación")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo borrar, intente nuevamente más tarde.\"}");
        }
    }

    // Admin anular Cuenta - Se necesita el ID de la cuenta a anular y un boolean ( true - false )
    @Operation(summary = "Anular una cuenta", description = "Anula una cuenta por su ID y estado (verdadero/falso)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta anulada exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de cuenta inválido o error al anular")
    })
    @PutMapping("/annulledAccount/{id_account}/{annulled}")
    public ResponseEntity<?> annulledAccount(@PathVariable Long id_account, @PathVariable Boolean annulled) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.annulledAccount(id_account, annulled));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo anular la cuenta, intente nuevamente.\"}");
        }
    }

    // Cargar Saldo en cuenta - Se necesita el ID de la Account a cargar y el monto ( balance ).
    @Operation(summary = "Cargar saldo en una cuenta", description = "Carga un monto especificado al saldo de una cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo cargado exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de cuenta inválido o monto de saldo inválido")
    })
    @PutMapping("/loadBalance/account/{id_account}/balance/{balance}")
    public ResponseEntity<?> loadBalance(@PathVariable Long id_account, @PathVariable Double balance) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.loadBalance(id_account, balance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo agregar el saldo a la cuenta, intente nuevamente más tarde.\"}");
        }
    }

    // Descontar Saldo en cuenta - Se necesita el ID de la Account a descontar y el monto ( discount ).
    @Operation(summary = "Descontar saldo de una cuenta", description = "Descuenta un monto especificado del saldo de una cuenta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Saldo descontado exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de cuenta inválido o monto de descuento inválido")
    })
    @PutMapping("/discountBalance/account/{id_account}/balance/{balance}")
    public ResponseEntity<?> discountBalance(@PathVariable Long id_account, @PathVariable Double balance) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(accountService.discountBalance(id_account, balance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al intentar descontar el saldo de la cuenta, por favor intente nuevamente.\"}");
        }
    }
}