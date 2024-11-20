package org.example.microservice_parking.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.microservice_parking.dtos.ParkingDto;
import org.example.microservice_parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parkings")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Operation(summary = "Obtener todos los estacionamientos", description = "Devuelve una lista de todos los estacionamientos disponibles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingDto.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron estacionamientos")
    })
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obtener los estacionamiento.\"}");
        }
    }


    @Operation(summary = "Registrar un nuevo estacionamiento", description = "Registra un nuevo estacionamiento con los datos proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamiento registrado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error al registrar")
    })
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ParkingDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    @Operation(summary = "Modificar un estacionamiento", description = "Actualiza los datos de un estacionamiento existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamiento actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error al actualizar")
    })
    @PutMapping(value = "/{id_parking}")
    public ResponseEntity<?> update(@PathVariable Long id_parking, @RequestBody ParkingDto parking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.update(id_parking, parking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Eliminar un estacionamiento", description = "Elimina un estacionamiento por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamiento eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar")
    })
    @DeleteMapping(value = "/{id_parking}")
    public ResponseEntity<?> delete(@PathVariable Long id_parking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.delete(id_parking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar, intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Obtener estacionamiento por coordenadas",
            description = "Devuelve el estacionamiento segun las coordenadas especificadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado"),
            @ApiResponse(responseCode = "404", description = "No se encontró ningún estacionamiento")
    })
    @GetMapping(value = "/parkingByLatitudeAndLongitude/{latitude}/{longitude}")
    public ResponseEntity<?> getParkingByLatitudeAndLongitude(@PathVariable Double latitude, @PathVariable Double longitude) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParkingByLatitudeAndLongitude(latitude, longitude));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @Operation(summary = "Obtener estacionamiento por ID", description = "Devuelve los datos de un estacionamiento dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estacionamiento encontrado"),
            @ApiResponse(responseCode = "404", description = "No se encontró el estacionamiento")
    })
    @GetMapping("/parkingById/{id_parking}")
    public ResponseEntity<?> getParkingById(@PathVariable Long id_parking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParkingById(id_parking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
