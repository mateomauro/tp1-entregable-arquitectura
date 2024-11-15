package org.example.microservice_trip.controllers;

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

    //get all trips
    @GetMapping("")
    public ResponseEntity<?> getAllTrip() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener los viajes.\"}");
        }
    }

    //delete a trip
    @DeleteMapping("/{id_trip}")
    public ResponseEntity<?> delete(@PathVariable Long id_trip) {
        try {
            tripService.delete(id_trip);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar el viaje, revise e intente nuevamente.\"}");
        }
    }

    //modify a trip
    @PutMapping("/{id_trip}")
    public ResponseEntity<?> update(@PathVariable Long id_trip, @RequestBody Trip trip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.update(id_trip, trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    //START A TRIP
    @PostMapping("/startTrip/user/{id_user}/account/{id_account}/scooter/{id_scooter}")
    public ResponseEntity<?> save(@PathVariable Long id_user, @PathVariable Long id_account, @PathVariable Long id_scooter) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.save(id_user, id_account, id_scooter));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //END A TRIP
    @PutMapping("/endTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> endTrip(@PathVariable Long id_user, @PathVariable Long id_account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.endTrip(id_user, id_account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    //PAUSE A TRIP
    @PostMapping("/pauseTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> pauseTrip(@PathVariable Long id_user, @PathVariable Long id_account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.pauseTrip(id_user, id_account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //UNPAUSE A TRIP
    @PutMapping("/unpauseTrip/user/{id_user}/account/{id_account}")
    public ResponseEntity<?> unpauseTrip(@PathVariable Long id_user, @PathVariable Long id_account) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.unpauseTrip(id_user, id_account));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo despausar.\"}");

        }
    }

    //GET ALL PAUSE BY SCOOTER
    @GetMapping("/pauseByScooter")
    public ResponseEntity<?> getAllPauseByScooter() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getAllPauseByScooter());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener las pausas por monopatin.\"}");
        }
    }

    //GET ALL PAUSE BY ID TRIP
    @GetMapping("/pauseByIdTrip/trip/{id_trip}")
    public ResponseEntity<?> getAllPauseByIdTrip(@PathVariable Long id_trip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getAllPauseByIdTrip(id_trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener las pausas por id trip.\"}");
        }
    }

    //GET TRIP BY ID
    @GetMapping("/tripsById/{id_trip}")
    public ResponseEntity<?> getTripIdTrip(@PathVariable Long id_trip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripByIdTrip(id_trip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde al obtener los km por id trip.\"}");
        }
    }

    //GET SCOOTER WITH MORE TRIPS BY YEAR AND COUNT
    @GetMapping("/tripByYearAndCountTrip/{year}/{count}")
    public ResponseEntity<?> getTripByYearAndCountTrip(@PathVariable int year, @PathVariable int count) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tripService.getTripByYearAndCountTrip(year, count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
