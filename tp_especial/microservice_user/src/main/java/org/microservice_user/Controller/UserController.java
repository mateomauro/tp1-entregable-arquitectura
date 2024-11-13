package org.microservice_user.Controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.microservice_user.Dtos.AccountRequestDTO;
import org.microservice_user.Dtos.UserRequestDTO;
import org.microservice_user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    // CRUD - READ
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los usuarios.\"}");
        }
    }

    @GetMapping("/{id_user}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id_user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id_user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo el usuario por ID.\"}");
        }
    }

    // CRUD - CREATE
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserRequestDTO user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    // CRUD - UPDATE
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    // CRUD - DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo borrar, intente nuevamente más tarde.\"}");
        }
    }

    // Crear cuenta de Usuario
    @PostMapping("/createAccount/{id_user}")
    public ResponseEntity<?> createAccount(@PathVariable Long id_user, @RequestBody @Valid AccountRequestDTO accountDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.addAccountToUser(id_user, accountDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear la cuenta en el usuario, intente nuevamente más tarde.\"}");
        }
    }

    // Asociar Cuenta porque puedo agregarle una cuenta a un user si ya está creada.
    @PostMapping("/linkAccount/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> linkAccount(@PathVariable Long id_user, @PathVariable Long id_account){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.linkAccountToUser(id_user, id_account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }

    // Comenzar Viaje ( Activar Monopatin ) - Se necesita el ID del User.
    @PostMapping("/startTrip/user/{id_user}")
    public ResponseEntity<?> startTrip(@PathVariable Long id_user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.startTrip(id_user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo comenzar un viaje con el monopatín, intente nuevamente más tarde.\"}");
        }
    }

    // Finalizar Viaje ( Desactivar Monopatin ) - Se necesita el ID del User.
    @PostMapping("/endTrip/user/{id_user}")
    public ResponseEntity<?> endTrip(@PathVariable Long id_user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.endTrip(id_user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar el viaje del monopatín, intente nuevamente más tarde.\"}");
        }
    }

    // Pausar Viaje ( Pausar Monopatín ) - Se necesita el ID del User.
    @PostMapping("/pauseTrip/user/{id_user}")
    public ResponseEntity<?> pauseTrip(@PathVariable Long id_user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.pauseTrip(id_user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }
    // Despausar Viaje ( Despausar Monopatín ) - Se necesita el ID del User.
    @PostMapping("/unpauseTrip/user/{id_user}")
    public ResponseEntity<?> unpauseTrip(@PathVariable Long id_user){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.unpauseTrip(id_user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }

    // Obtener Monopatines Cercanos - Se necesita el ID del User y el radius a buscar.
    @GetMapping("/scootersNearby/user/{id_user}/radius/{radius}")
    public ResponseEntity<?> getScootersNearby(@PathVariable Long id_user, @PathVariable Double radius){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getScooterNearby(id_user, radius));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }
}