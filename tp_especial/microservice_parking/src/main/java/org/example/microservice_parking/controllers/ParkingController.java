package org.example.microservice_parking.controllers;

import lombok.RequiredArgsConstructor;
import org.example.microservice_parking.dtos.ParkingDto;
import org.example.microservice_parking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkings")
@RequiredArgsConstructor
public class ParkingController {
    private final ParkingService parkingService;

    //FIND ALL PARKING
    @GetMapping("")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obtener los estacionamiento.\"}");
        }
    }


    //REGISTER A PARKING
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ParkingDto entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }


    //MODIFY A PARKING
    @PutMapping("/{id_parking}")
    public ResponseEntity<?> update(@PathVariable long id_parking, @RequestBody ParkingDto parking){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.update(id_parking,parking));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }

    //DELETE A PARKING
    @DeleteMapping("/{id_parking}")
    public ResponseEntity<?> delete(@PathVariable long id_parking) {
        try {
            parkingService.delete(id_parking);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo eliminar, intente nuevamente.\"}");
        }
    }

    //GET PARKING BY LATITUD AND LONGITUD
    @GetMapping("/ParkingByLatitudAndLongitud/{latitud}/{longitud}")
    public ResponseEntity<?> getParkingByLatitudAndLongitud(@PathVariable double latitud, @PathVariable double longitud){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParkingByLatitudAndLongitud(latitud, longitud));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    //GET PARKING BY ID
    @GetMapping("/ParkingById/{id_parking}")
    public ResponseEntity<?> getParkingById(@PathVariable long id_parking){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(parkingService.getParkingById(id_parking));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
