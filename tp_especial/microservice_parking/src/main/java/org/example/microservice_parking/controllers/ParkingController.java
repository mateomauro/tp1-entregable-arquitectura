package org.example.microservice_parking.controllers;

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

    //FIND ALL PARKING
    @GetMapping("")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obtener los estacionamiento.\"}");
        }
    }


    //REGISTER A PARKING
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ParkingDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    //MODIFY A PARKING
    @PutMapping("/{id_parking}")
    public ResponseEntity<?> update(@PathVariable long id_parking, @RequestBody ParkingDto parking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.update(id_parking, parking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    //DELETE A PARKING
    @DeleteMapping("/{id_parking}")
    public ResponseEntity<?> delete(@PathVariable long id_parking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.delete(id_parking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar, intente nuevamente.\"}");
        }
    }

    //GET PARKING BY LATITUD AND LONGITUD
    @GetMapping("/parkingByLatitudeAndLongitude/{latitude}/{longitude}")
    public ResponseEntity<?> getParkingByLatitudeAndLongitude(@PathVariable double latitude, @PathVariable double longitude) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParkingByLatitudeAndLongitude(latitude, longitude));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    //GET PARKING BY ID
    @GetMapping("/parkingById/{id_parking}")
    public ResponseEntity<?> getParkingById(@PathVariable long id_parking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParkingById(id_parking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
