package microservicioAdmin.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "Guardar una tarifa", description = "Guarda una nueva tarifa en el sistema.")
    @ApiResponse(responseCode = "200", description = "Tarifa guardada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar la tarifa")
    @PostMapping("")
    public ResponseEntity<?> saveRate(@RequestBody RateDTO rate) { //el add agrego en rate entidad no?
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveRate(rate));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Editar el precio de una tarifa", description = "Actualiza el precio de una tarifa existente.")
    @ApiResponse(responseCode = "200", description = "Tarifa actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada")
    @PutMapping("/updateRate/{idRate}")
    public ResponseEntity<?> updateRate(@PathVariable Long idRate, @RequestBody Rate rateNew) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateRate(idRate, rateNew));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para editar el precio de la tarifa.\"}");
        }
    }

    @Operation(summary = "Eliminar una tarifa", description = "Elimina una tarifa del sistema.")
    @ApiResponse(responseCode = "200", description = "Tarifa eliminada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al eliminar la tarifa")
    @DeleteMapping("/deleteRate/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteRate(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error, revise los campos e intente nuevamente.");
        }
    }

    @Operation(summary = "Obtener todas las tarifas", description = "Recupera todas las tarifas registradas en el sistema.")
    @ApiResponse(responseCode = "200", description = "Tarifas recuperadas correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron tarifas")
    @GetMapping("")
    public ResponseEntity<?> getAllRate() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos las tarifas.\"}");
        }
    }

// BILLING
    @Operation(summary = "Obtener todas las facturaciones", description = "Recupera todas las facturaciones registradas.")
    @ApiResponse(responseCode = "200", description = "Facturaciones recuperadas correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron facturaciones")
    @GetMapping("/getBillings")
    public ResponseEntity<?> getAllBillings() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllBillings());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos las tarifas.\"}");
        }
    }

    @Operation(summary = "Guardar una facturación", description = "Guarda una nueva facturación.")
    @ApiResponse(responseCode = "200", description = "Facturación guardada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al guardar la facturación")
    @PostMapping("/insertBilling")
    public ResponseEntity<?> saveBilling(@RequestBody Billing billing) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.saveBilling(billing));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar la facturacion, revise los campos e intente nuevamente.\"}");
        }
    }

    @Operation(summary = "Eliminar facturación", description = "Elimina una facturación existente.")
    @ApiResponse(responseCode = "200", description = "Facturación eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Facturación no encontrada")
    @DeleteMapping("/deleteBilling/{idBilling}")
    public ResponseEntity<?> deleteBilling(@PathVariable Long idBilling) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteBilling(idBilling));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para eliminar la facturación con id " + idBilling + ".\"}");
        }
    }

//MONOPATIN:

    @Operation(summary = "Insertar un monopatín", description = "Agrega un nuevo monopatín al sistema.")
    @ApiResponse(responseCode = "200", description = "Monopatín insertado correctamente")
    @ApiResponse(responseCode = "400", description = "Error al insertar el monopatín")
    @PostMapping("/insertScooter")
    public ResponseEntity<ScooterDTO> insertScooter(@RequestBody ScooterDTO scooterNew) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.insertScooter(scooterNew));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Operation(summary = "Editar monopatín", description = "Actualiza la información de un monopatín.")
    @ApiResponse(responseCode = "200", description = "Monopatín actualizado correctamente")
    @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    @PutMapping("/updateScooter/{idScooter}")
    public ResponseEntity<?> updateScooter(@PathVariable Long idScooter, @RequestBody ScooterDTO scooterDTO) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateScooter(idScooter, scooterDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para editar los monopatines.\"}");
        }
    }


    @Operation(summary = "Eliminar monopatín", description = "Elimina un monopatín por su ID.")
    @ApiResponse(responseCode = "200", description = "Monopatín eliminado correctamente")
    @ApiResponse(responseCode = "404", description = "Monopatín no encontrado")
    @DeleteMapping("/deleteScooter/{idScooter}")
    public ResponseEntity<?> deleteScooter(@PathVariable Long idScooter) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteScooter(idScooter));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para eliminar el monopatin con id " + idScooter + ".\"}");
        }
    }

    @Operation(summary = "Obtener todos los monopatines", description = "Recupera todos los monopatines registrados.")
    @ApiResponse(responseCode = "200", description = "Monopatines recuperados correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron monopatines")
    @GetMapping("/scooters")
    public ResponseEntity<?> getAllScooters() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllScooter());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los monopatines.\"}");
        }
    }

//PARKING:

    @Operation(summary = "Insertar una nueva parada", description = "Crea una nueva parada (estacionamiento) en el sistema.")
    @ApiResponse(responseCode = "200", description = "Parada insertada correctamente")
    @ApiResponse(responseCode = "400", description = "Error al insertar la parada")
    @PostMapping("/insertParking")
    public ResponseEntity<ParkingDTO> insertParking(@RequestBody ParkingDTO parkingNew) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.insertParking(parkingNew));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Operation(summary = "Editar una parada existente", description = "Actualiza la información de una parada (estacionamiento).")
    @ApiResponse(responseCode = "200", description = "Parada actualizada correctamente")
    @ApiResponse(responseCode = "404", description = "Parada no encontrada")
    @PutMapping("/updateParking/{idParking}")
    public ResponseEntity<?> updateParking(@PathVariable Long idParking, @RequestBody ParkingDTO parkingNew) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateParking(idParking, parkingNew));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para editar el estacionamiento.\"}");
        }
    }

    @Operation(summary = "Eliminar una parada", description = "Elimina una parada (estacionamiento) por su ID.")
    @ApiResponse(responseCode = "200", description = "Parada eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Parada no encontrada")
    @DeleteMapping("/deleteParking/{idParking}")
    public ResponseEntity<?> deleteParking(@PathVariable Long idParking) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteParking(idParking));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para eliminar la parada con id " + idParking + ".\"}");
        }
    }

    @Operation(summary = "Obtener todas las paradas", description = "Recupera todas las paradas (estacionamientos) registrados.")
    @ApiResponse(responseCode = "200", description = "Paradas recuperadas correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron paradas")
    @GetMapping("/parkings")
    public ResponseEntity<?> getAllParking() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findAllParking());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los estacionamientos.\"}");
        }
    }

    @Operation(summary = "Obtener una parada por ID", description = "Recupera una parada (estacionamiento) por su ID.")
    @ApiResponse(responseCode = "200", description = "Parada recuperada correctamente")
    @ApiResponse(responseCode = "404", description = "Parada no encontrada")
    @GetMapping("/parkingById/{id}")
    public ResponseEntity<?> getParkingById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.findParkingById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los estacionamientos.\"}");
        }
    }

//OTROS METODOS:

    @Operation(summary = "Calcular el costo de un viaje", description = "Calcula el costo de un viaje basado en la tarifa y los kilómetros recorridos.")
    @ApiResponse(responseCode = "200", description = "Costo calculado correctamente")
    @ApiResponse(responseCode = "404", description = "Tarifa o viaje no encontrado")
    @GetMapping("/calculateCost/{id_trip}/{km_traveled}")
    public ResponseEntity<?> calculateRateOfTrip(@PathVariable Long id_trip, @PathVariable Double km_traveled) throws Exception {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.calculateRateOfTrip(id_trip, km_traveled));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente mas tarde para calcular el costo del viaje.\"}");
        }
    }

    @Operation(summary = "Anular una cuenta", description = "Anula una cuenta de usuario con base en su ID.")
    @ApiResponse(responseCode = "200", description = "Cuenta anulada correctamente")
    @ApiResponse(responseCode = "404", description = "Cuenta no encontrada")
    @PutMapping("/accounts/{idAccount}/{annul}")
    public ResponseEntity<?> annulledAccount(@PathVariable Long idAccount, @PathVariable Boolean annul) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.annulledAccount(idAccount, annul));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudo cancelar la cuenta.\"}");
        }
    }

    @Operation(summary = "Realizar facturación entre meses", description = "Obtiene el monto facturado entre dos meses específicos de un año.")
    @ApiResponse(responseCode = "200", description = "Monto facturado recuperado correctamente")
    @ApiResponse(responseCode = "404", description = "Facturación no encontrada")
    @GetMapping("/billing/{year}/{monthOne}/{monthTwo}")
    public ResponseEntity<?> getBilledAmount(@PathVariable int year, @PathVariable int monthOne, @PathVariable int monthTwo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.getBilledAmount(year, monthOne, monthTwo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudieron traer la factquracion con ese en ese rango de meses.\"}");
        }
    }

    @Operation(summary = "Ajustar precios a partir de una fecha", description = "Realiza un ajuste de precios para las tarifas a partir de una fecha específica.")
    @ApiResponse(responseCode = "200", description = "Ajuste de precios realizado correctamente")
    @ApiResponse(responseCode = "404", description = "Error en el ajuste de precios")
    @PostMapping("/updateRate/byDate")
    public ResponseEntity<?> updateRateForDate(@RequestBody Rate rateNew) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.updateRateForDate(rateNew));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudo realizar ese ajuste de precio\"}");
        }
    }

    @Operation(summary = "Obtener monopatines con más de X viajes", description = "Recupera los monopatines que han tenido más de X viajes en un año determinado.")
    @ApiResponse(responseCode = "200", description = "Monopatines recuperados correctamente")
    @ApiResponse(responseCode = "404", description = "No se encontraron monopatines con esa cantidad de viajes")
    @GetMapping("/scooters/{year}/{countTrip}")
    public ResponseEntity<?> fetchScootersByTripsInYear(@PathVariable int year, @PathVariable int countTrip) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.fetchScootersByTripsInYear(year, countTrip));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encontraron monopatines con esa cantidad de viajes, en dicho año\"}");
        }
    }

    @Operation(summary = "Contar monopatines en operación y en mantenimiento", description = "Obtiene la cantidad de monopatines que están en operación y en mantenimiento.")
    @ApiResponse(responseCode = "200", description = "Cantidad de monopatines recuperada correctamente")
    @ApiResponse(responseCode = "404", description = "No se pudo obtener el reporte")
    @GetMapping("/scooters/inOperation")
    public ResponseEntity<?> countScootersInOperationAndMaintenance() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(adminService.countScootersInOperationAndMaintenance());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se pudieron encontrar el reporte de monopatines\"}");
        }
    }
}
