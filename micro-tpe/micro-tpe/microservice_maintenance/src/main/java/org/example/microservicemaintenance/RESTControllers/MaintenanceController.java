package org.example.microservicemaintenance.RESTControllers;

import org.example.microservicemaintenance.Services.MaintenanceService;
import org.example.microservicemaintenance.Entities.Maintenance;
import org.example.microservicemaintenance.exception.MaintenanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("")
    public ResponseEntity<?> getAllMaintenance(){
        try {
           return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo realizar el pedido por algun motivo");
        }
    }

    @GetMapping("/{id_maintenance}")
    public ResponseEntity<?> getOneMaintenance(@PathVariable long id_maintenance){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.getOne(id_maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: no se pudo encontrar ese id");
        }
    }

    @GetMapping("/generateReport")
    public ResponseEntity<?> generateReportMaintenance(@RequestParam(value = "includePauses", required = false) String includePauses) {
        try {
            // api/maintenances/generateReport?incluedePauses=true
            boolean includePausesFlag = "true".equals(includePauses);
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.getReport(includePausesFlag));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo generar el reporte");
        }
    }

    @GetMapping("/scooterNotRepaired")
    public ResponseEntity<?> getAllScooterMaintenance(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.getAllScooterNotReapair());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo encontrar ese id");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> repairSkateBoard(){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.checkMaintenance());
        } catch (MaintenanceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id_maintenance}")
    public ResponseEntity<?>deleteOneMaintenance(@PathVariable long id_maintenance){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.delete(id_maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar, revise el pedido");
        }
    }

    @PutMapping("/{id_maintenance}")
    public ResponseEntity<?> modificOneMaintenance(@PathVariable long id_maintenance, @RequestBody Maintenance maintenance){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.update(id_maintenance, maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pude modificar, revise de nuevo los campos");
        }
    }
}
