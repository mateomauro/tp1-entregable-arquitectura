package com.tpe.micro.scooters.RESTControllers;

import com.tpe.micro.scooters.DTOs.ScooterRequestDTO;
import com.tpe.micro.scooters.Services.ScooterService;
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

    //  Devuelve una lista de todos los monopatines
    @GetMapping("")
    public ResponseEntity<?> getAllScooters() {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    //  Devuelve un monopatin de igual identificador
    @GetMapping("/{id}")
    public ResponseEntity<?> getScooterById(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getById(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    //  Añade un nuevo monopatin
    @PostMapping("")
    public ResponseEntity<?> insertScooter(@RequestBody @Validated ScooterRequestDTO scooterRequestDTO) {
        try {
            return ResponseEntity.status(CREATED).body(this.scooterService.insert(scooterRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
        }
    }

    //  Modifica el monopatin
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScooter(@PathVariable long id, @RequestBody ScooterRequestDTO scooterRequestDTO) throws Exception {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.update(id, scooterRequestDTO));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    //  Elimina el monopatin
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScooter(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    //  Activa el monopatin
    @GetMapping("/{id}/activate")
    public ResponseEntity<?> activateScooter(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.activateScooter(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    //  Desactiva el monopatin
    @GetMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateScooter(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.deactivateScooter(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    // Setea el monopatin en mantenimiento
    @GetMapping("/{id}/toMaintenance")
    public ResponseEntity<?> scooterToMaintenance(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.addToMaintenance(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    // Setea el monopatin fuera de mantenimiento
    @GetMapping("/{id}/leaveMaintenance")
    public ResponseEntity<?> removeScooterMaintenance(@PathVariable long id) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.leaveMaintenance(id));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    //  Listado de los monopatines cercanos a esa latitud y longitud, en un radio dado
    @GetMapping("/getScootersNearby/latitude/{latitude}/longitude/{longitude}/radius/{radius}")
    public ResponseEntity<?> getScooterNearby(@PathVariable Double latitude, @PathVariable Double longitude, @PathVariable Double radius) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.findNearby(latitude, longitude, radius));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    // Obtiene el monopatin mas cercano en cuanto a una latitud y longitud dada
    @GetMapping("/getScooterNearest/latitude/{latitude}/longitude/{longitude}")
    public ResponseEntity<?> getScooterNearest(@PathVariable Double latitude, @PathVariable Double longitude) {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.findAScooterNear(latitude, longitude));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    // Lista los monopatines ordenados por kilometros
    @GetMapping("/mostKm")
    public ResponseEntity<?> getMoreKm() {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getAllByKm());
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

    // Lista los monopatines que no estan en mantenimiento
    @GetMapping("/actives")
    public ResponseEntity<?> getActives() {
        try {
            return ResponseEntity.status(OK).body(this.scooterService.getActives());
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
        }
    }

}
