package org.example.microservice_trip.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.example.microservice_trip.dtos.TripDto;
import org.example.microservice_trip.entities.Pause;
import org.example.microservice_trip.entities.Trip;
import org.example.microservice_trip.service.TripService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Time;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @Operation(summary = "Obtener todos los viajes", description = "Devuelve una lista de todos los viajes registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trip.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("")
    public ResponseEntity<?> getAllTrip() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener los viajes.\"}");
        }
    }

    @Operation(summary = "Eliminar un viaje", description = "Elimina un viaje específico por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje eliminado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al intentar eliminar el viaje")
    })
    @DeleteMapping("/{id_trip}")
    public ResponseEntity<?> delete(@PathVariable Long id_trip) {
        try {
            tripService.delete(id_trip);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el viaje, revise e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Modificar un viaje", description = "Actualiza los detalles de un viaje específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trip.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error al actualizar el viaje")
    })
    @PutMapping("/{id_trip}")
    public ResponseEntity<?> update(@PathVariable Long id_trip, @RequestBody Trip trip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.update(id_trip, trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Iniciar un viaje", description = "Inicia un viaje asociando un usuario, cuenta y scooter.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Viaje iniciado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trip.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error al iniciar el viaje")
    })
    @PostMapping("/startTrip/user/{id_user}/account/{id_account}/scooter/{id_scooter}")
    public ResponseEntity<?> save(@PathVariable Long id_user, @PathVariable Long id_account, @PathVariable Long id_scooter) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.save(id_user, id_account, id_scooter));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Finalizar un viaje", description = "Finaliza un viaje en curso para un usuario y cuenta específicos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje finalizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o error al finalizar el viaje")
    })
    @PutMapping("/endTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> endTrip(@PathVariable Long id_user, @PathVariable Long id_account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.endTrip(id_user, id_account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Pausar un viaje", description = "Pausa un viaje en curso para un usuario y cuenta específicos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje pausado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al pausar el viaje")
    })
    @PostMapping("/pauseTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> pauseTrip(@PathVariable Long id_user, @PathVariable Long id_account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.pauseTrip(id_user, id_account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Reanudar un viaje", description = "Reanuda un viaje previamente pausado para un usuario y cuenta específicos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje reanudado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Error al reanudar el viaje")
    })
    @PutMapping("/unpauseTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> unpauseTrip(@PathVariable Long id_user, @PathVariable Long id_account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.unpauseTrip(id_user, id_account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo despausar.\"}");

        }
    }

    @Operation(summary = "Obtener todas las pausas de los scooters", description = "Devuelve una lista de todas las pausas realizadas por scooters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pausas obtenidas exitosamente",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron pausas")
    })
    @GetMapping("/pauseByScooter")
    public ResponseEntity<?> getAllPauseByScooter() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getAllPauseByScooter());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener las pausas por monopatin.\"}");
        }
    }

    @Operation(summary = "Obtener todas las pausas por ID de viaje", description = "Devuelve una lista de todas las pausas realizadas para un viaje específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pausas obtenidas exitosamente",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron pausas para el ID de viaje proporcionado")
    })
    @GetMapping("/pauseByIdTrip/trip/{id_trip}")
    public ResponseEntity<?> getAllPauseByIdTrip(@PathVariable Long id_trip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getAllPauseByIdTrip(id_trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener las pausas por id trip.\"}");
        }
    }

    @Operation(summary = "Obtener un viaje por ID", description = "Devuelve los detalles de un viaje específico dado su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Viaje obtenido exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Trip.class))),
            @ApiResponse(responseCode = "404", description = "No se encontró un viaje con el ID proporcionado")
    })
    @GetMapping("/tripsById/{id_trip}")
    public ResponseEntity<?> getTripIdTrip(@PathVariable Long id_trip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripByIdTrip(id_trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener los km por id trip.\"}");
        }
    }

    @Operation(summary = "Obtener scooters con más viajes por año y cantidad", description = "Devuelve una lista de scooters que han realizado más viajes en un año específico, con un mínimo de cantidad especificada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Scooters obtenidos exitosamente",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "No se encontraron scooters que cumplan con los criterios")
    })
    @GetMapping("/tripByYearAndCountTrip/{year}/{count}")
    public ResponseEntity<?> getTripByYearAndCountTrip(@PathVariable int year, @PathVariable int count) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripByYearAndCountTrip(year, count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
