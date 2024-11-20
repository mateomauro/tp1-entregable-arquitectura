package org.microservice_user.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtener todos los usuarios", description = "Obtiene todos los usuarios del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de todos los usuarios"),
            @ApiResponse(responseCode = "404", description = "Error al obtener los usuarios")
    })
    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los usuarios.\"}");
        }
    }

    @Operation(summary = "Obtener usuario por ID", description = "Obtiene un usuario específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado por ID"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/{id_user}")
    public ResponseEntity<?> getUserByID(@PathVariable Long id_user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id_user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo el usuario por ID.\"}");
        }
    }

    @Operation(summary = "Agregar un nuevo usuario", description = "Crea un nuevo usuario con los datos proporcionados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos proporcionados")
    })
    @PostMapping("")
    public ResponseEntity<?> addUser(@RequestBody @Valid UserRequestDTO user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.addUser(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Actualizar usuario", description = "Actualiza los detalles de un usuario existente por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de usuario o datos inválidos")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO user){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id, user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "ID de usuario inválido o error en la eliminación")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(userService.deleteById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo borrar, intente nuevamente más tarde.\"}");
        }
    }

    @Operation(summary = "Crear cuenta para un usuario", description = "Crea una cuenta para un usuario ya existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta creada exitosamente para el usuario"),
            @ApiResponse(responseCode = "400", description = "Error al crear la cuenta o usuario no encontrado")
    })
    @PostMapping("/createAccount/{id_user}")
    public ResponseEntity<?> createAccount(@PathVariable Long id_user, @RequestBody @Valid AccountRequestDTO accountDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.addAccountToUser(id_user, accountDTO));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo crear la cuenta en el usuario, intente nuevamente más tarde.\"}");
        }
    }

    @Operation(summary = "Vincular cuenta a usuario", description = "Asocia una cuenta existente a un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cuenta vinculada al usuario exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al vincular la cuenta al usuario")
    })
    @PostMapping("/linkAccount/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> linkAccount(@PathVariable Long id_user, @PathVariable Long id_account){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.linkAccountToUser(id_user, id_account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }

    // Comenzar Viaje ( Activar Monopatin ) - Se necesita el ID del User.
    @Operation(summary = "Comenzar un viaje", description = "Inicia un viaje con el monopatín para el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje iniciado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al iniciar el viaje o monopatín no disponible")
    })
    @PostMapping("/startTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> startTrip(@PathVariable Long id_user, @PathVariable Long id_account){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.startTrip(id_user, id_account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo comenzar un viaje con el monopatín, intente nuevamente más tarde.\"}");
        }
    }

    // Finalizar Viaje ( Desactivar Monopatin ) - Se necesita el ID del User.
    @Operation(summary = "Finalizar un viaje", description = "Finaliza un viaje con el monopatín para el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje finalizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al finalizar el viaje")
    })
    @PostMapping("/endTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> endTrip(@PathVariable Long id_user, @PathVariable Long id_account){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.endTrip(id_user, id_account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo finalizar el viaje del monopatín, intente nuevamente más tarde.\"}");
        }
    }

    // Pausar Viaje ( Pausar Monopatín ) - Se necesita el ID del User.
    @Operation(summary = "Pausar un viaje", description = "Pausa el viaje con el monopatín para el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje pausado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al pausar el viaje")
    })
    @PostMapping("/pauseTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> pauseTrip(@PathVariable Long id_user, @PathVariable Long id_account){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.pauseTrip(id_user, id_account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }

    // Despausar Viaje ( Despausar Monopatín ) - Se necesita el ID del User.
    @Operation(summary = "Despausar un viaje", description = "Despausa el viaje con el monopatín para el usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje despausado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al despausar el viaje")
    })
    @PostMapping("/unpauseTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> unpauseTrip(@PathVariable Long id_user, @PathVariable Long id_account){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.unpauseTrip(id_user, id_account));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }

    // Obtener Monopatines Cercanos - Se necesita el ID del User y el radius a buscar.
    @Operation(summary = "Obtener monopatines cercanos", description = "Obtiene una lista de monopatines cercanos a la ubicación del usuario según el radio proporcionado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de monopatines cercanos obtenida exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al obtener monopatines cercanos, datos inválidos o radio incorrecto")
    })
    @GetMapping("/scootersNearby/user/{id_user}/radius/{radius}")
    public ResponseEntity<?> getScootersNearby(@PathVariable Long id_user, @PathVariable Double radius){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.getScooterNearby(id_user, radius));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo vincular la cuenta con el usuario, intente nuevamente más tarde.\"}");
        }
    }
}