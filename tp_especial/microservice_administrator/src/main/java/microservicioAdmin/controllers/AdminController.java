package microservicioAdmin.controllers;

import lombok.RequiredArgsConstructor;
import microservicioAdmin.dto.RateDTO;
import microservicioAdmin.entities.Billing;
import microservicioAdmin.entities.Rate;
import microservicioAdmin.feignClients.model.ParkingDTO;
import microservicioAdmin.feignClients.model.ScooterDTO;
import microservicioAdmin.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

//TARIFAS:

    //Guardar una tarifa
    @PostMapping("")
    public ResponseEntity<?> saveRate(@RequestBody RateDTO rate) { //el add agrego en rate entidad no?
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveRate(rate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    //Editar el precio de una tarifa
    @PutMapping("/updateRate/{idRate}")
    public ResponseEntity<?> updateRate(@PathVariable Long idRate, @RequestBody Rate rateNew) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateRate(idRate, rateNew));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para editar el precio de la tarifa.\"}");
        }
    }

    //Eliminar tarifa
    @DeleteMapping("/deleteRate/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteRate(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    //Obtener todas las tarifas
    @GetMapping("")
    public ResponseEntity<?> getAllRate() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos las tarifas.\"}");
        }
    }

// BILLING

    //Guardar una tarifa
    @PostMapping("/insertBilling")
    public ResponseEntity<?> saveBilling(@RequestBody Billing billing) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveBilling(billing));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar la facturacion, revise los campos e intente nuevamente.\"}");
        }
    }

//MONOPATIN:

    //insertar un monopatin
    @PostMapping("/insertScooter")
    public ResponseEntity<ScooterDTO> insertScooter(@RequestBody ScooterDTO scooterNew) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.insertScooter(scooterNew));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //editar scooter
    @PutMapping("/updateScooter/{idScooter}")
    public ResponseEntity<?> updateScooter(@PathVariable Long idScooter, @RequestBody ScooterDTO scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateScooter(idScooter, scooterDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para editar los monopatines.\"}");
        }
    }


    //eliminar scooter con el id que se pasa por parametro
    @DeleteMapping("/deleteScooter/{idScooter}")
    public ResponseEntity<?> deleteScooter(@PathVariable Long idScooter) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteScooter(idScooter));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para eliminar el monopatin con id " + idScooter + ".\"}");
        }
    }

    // traerme todos los monopatines
    @GetMapping("/scooters")
    public ResponseEntity<?> getAllScooters() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllScooter());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los monopatines.\"}");
        }
    }

//PARKING:

    //insertar una parada
    @PostMapping("/insertParking")
    public ResponseEntity<ParkingDTO> insertParking(@RequestBody ParkingDTO parkingNew) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.insertParking(parkingNew));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    //editar una parada
    @PutMapping("/updateParking/{idParking}")
    public ResponseEntity<?> updateParking(@PathVariable Long idParking, @RequestBody ParkingDTO parkingNew) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateParking(idParking, parkingNew));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para editar el estacionamiento.\"}");
        }
    }

    //eliminar la parada con el id que se pasa por parametro
    @DeleteMapping("/deleteParking/{idParking}")
    public ResponseEntity<?> deleteParking(@PathVariable Long idParking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteParking(idParking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para eliminar la parada con id " + idParking + ".\"}");
        }
    }

    // traerme todos los monopatines
    @GetMapping("/parkings")
    public ResponseEntity<?> getAllParking() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllParking());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los estacionamientos.\"}");
        }
    }

    // estacionamiento por id
    @GetMapping("/parkingById/{id}")
    public ResponseEntity<?> getParkingById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findParkingById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los estacionamientos.\"}");
        }
    }

//OTROS METODOS:

    //precio de tarifa
    @GetMapping("/calculateCost/{idViaje}")
    public ResponseEntity<?> calculateRateOfTrip(@PathVariable Long idViaje) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.calculateRateOfTrip(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde para calcular el costo del viaje.\"}");
        }
    }

    //anular cuenta
    @PutMapping("/accounts/{idAccount}/{annul}")
    public ResponseEntity<?> annulledAccount(@PathVariable Long idAccount, @PathVariable Boolean annul) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.annulledAccount(idAccount, annul));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudo cancelar la cuenta.\"}");
        }
    }

    //Realizar facturacion entre 2 meses en un determinado año
    @GetMapping("/billing/{year}/{monthOne}/{monthTwo}")
    public ResponseEntity<?> getBilledAmount(@PathVariable int year, @PathVariable int monthOne, @PathVariable int monthTwo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getBilledAmount(year, monthOne, monthTwo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudieron traer la factquracion con ese en ese rango de meses.\"}");
        }
    }

    //ajuste de precios a partir de cierta fecha
    @PutMapping("/updateRate/byDate")
    public ResponseEntity<?> updateRateForDate(@RequestBody Rate rateNew) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateRateForDate(rateNew));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudo realizar ese ajuste de precio\"}");
        }
    }

    // monopatines con más de X viajes en un cierto año.
    @GetMapping("/scooters/{year}/{countTrip}")
    public ResponseEntity<?> fetchScootersByTripsInYear(@PathVariable int year, @PathVariable int countTrip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.fetchScootersByTripsInYear(year, countTrip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encontraron monopatines con esa cantidad de viajes, en dicho año\"}");
        }
    }

    // cantidad de monopatines en operacion y en mantenimiento
    @GetMapping("/scooters/inOperation")
    public ResponseEntity<?> countScootersInOperationAndMaintenance() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.countScootersInOperationAndMaintenance());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudieron encontrar el reporte de monopatines\"}");
        }
    }
}
