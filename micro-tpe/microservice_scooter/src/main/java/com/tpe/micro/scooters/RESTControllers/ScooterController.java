package com.tpe.micro.scooters.RESTControllers;

import com.tpe.micro.scooters.DTOs.ScooterRequestDTO;
import com.tpe.micro.scooters.DTOs.ScooterResponseDTO;
import com.tpe.micro.scooters.Services.ScooterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/scooters")
public class ScooterController {

    @Autowired
    private ScooterService scooterService;

    @Operation(summary = "Obtener todos los monopatines",
            description = "Devuelve una lista de todos los monopatines registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScooterResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("")
    public ResponseEntity<?> getAllScooters() {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtener un monopatín por ID",
            description = "Devuelve un monopatín basado en su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Monopatín encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScooterResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró el monopatín"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getScooterById(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Añadir un nuevo monopatín",
            description = "Crea y registra un nuevo monopatín.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Monopatín creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScooterResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("")
    public ResponseEntity<?> insertScooter(@RequestBody @Validated ScooterRequestDTO scooterRequestDTO) {
        try {
            return ResponseEntity.status(CREATED).body(this.scooterService.insert(scooterRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @Operation(summary = "Actualizar un monopatín",
            description = "Modifica los datos de un monopatín existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Monopatín actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ScooterResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró el monopatín"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScooter(@PathVariable long id, @RequestBody ScooterRequestDTO scooterRequestDTO) throws Exception {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.update(id, scooterRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Eliminar un monopatín",
            description = "Elimina un monopatín basado en su identificador único.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Monopatín eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró el monopatín"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScooter(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Activa un monopatín", description = "Cambia el estado de un monopatín a activo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín activado correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
    @GetMapping("/{id}/activate")
    public ResponseEntity<?> activateScooter(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.activateScooter(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Desactiva un monopatín", description = "Cambia el estado de un monopatín a inactivo.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín desactivado correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
    @GetMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateScooter(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.deactivateScooter(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Pone un monopatín en mantenimiento", description = "Marca un monopatín como en mantenimiento.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín puesto en mantenimiento correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
    @GetMapping("/{id}/toMaintenance")
    public ResponseEntity<?> scooterToMaintenance(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.addToMaintenance(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Saca un monopatín del mantenimiento", description = "Quita el estado de mantenimiento de un monopatín.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín fuera de mantenimiento correctamente"),
            @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    })
    @GetMapping("/{id}/leaveMaintenance")
    public ResponseEntity<?> removeScooterMaintenance(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.leaveMaintenance(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene monopatines cercanos", description = "Lista monopatines cercanos a una ubicación dada dentro de un radio.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatines cercanos obtenidos exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines")
    })
    @GetMapping("/getScootersNearby/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
    public ResponseEntity<?> getScooterNearby(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double radius) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.findNearby(latitude, longitude, radius));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Obtiene el monopatín más cercano", description = "Devuelve el monopatín más cercano a una ubicación específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Monopatín más cercano obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "No se encontró un monopatín")
    })
    @GetMapping("/getScooterNearest/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<?> getScooterNearest(@PathVariable Double latitude, @PathVariable Double longitude) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.findAScooterNear(latitude, longitude));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista monopatines por kilómetros recorridos", description = "Devuelve los monopatines ordenados por kilómetros recorridos.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines")
    })
    @GetMapping("/mostKm")
    public ResponseEntity<?> getMoreKm() {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getAllByKm());
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    @Operation(summary = "Lista monopatines activos", description = "Devuelve una lista de monopatines que no están en mantenimiento.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
            @ApiResponse(responseCode = "404", description = "No se encontraron monopatines activos")
    })
    @GetMapping("/actives")
    public ResponseEntity<?> getActives() {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getActives());
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }
}