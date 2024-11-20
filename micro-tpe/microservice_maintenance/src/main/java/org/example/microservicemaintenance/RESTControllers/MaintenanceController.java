package org.example.microservicemaintenance.RESTControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Obtiene todos los mantenimientos", description = "Devuelve todos los registros de mantenimiento de los monopatines.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mantenimientos obtenidos correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    @GetMapping("")
    public ResponseEntity<?> getAllMaintenance(){
        try {
           return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo realizar el pedido por algun motivo");
        }
    }

    @Operation(summary = "Obtiene un mantenimiento por ID", description = "Devuelve el detalle de un mantenimiento específico por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mantenimiento encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Mantenimiento no encontrado")
    })
    @GetMapping("/{id_maintenance}")
    public ResponseEntity<?> getOneMaintenance(@PathVariable long id_maintenance){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.getOne(id_maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: no se pudo encontrar ese id");
        }
    }

    @Operation(summary = "Genera un reporte de mantenimiento", description = "Genera un reporte de mantenimiento con opción de incluir pausas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reporte generado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al generar el reporte")
    })
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

    @Operation(summary = "Obtiene todos los scooters no reparados", description = "Devuelve todos los scooters que aún no han sido reparados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Scooters no reparados obtenidos correctamente"),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud")
    })
    @GetMapping("/scooterNotRepaired")
    public ResponseEntity<?> getAllScooterMaintenance(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.getAllScooterNotReapair());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo encontrar ese id");
        }
    }

    @Operation(summary = "Inicia mantenimiento de scooters", description = "Revisa los scooters y los pone en mantenimiento si cumplen con las condiciones.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mantenimiento iniciado correctamente"),
            @ApiResponse(responseCode = "400", description = "No hay scooters que necesiten mantenimiento")
    })
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

    @Operation(summary = "Elimina un mantenimiento por ID", description = "Elimina el registro de mantenimiento correspondiente al ID proporcionado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mantenimiento eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al eliminar el mantenimiento")
    })
    @DeleteMapping("/{id_maintenance}")
    public ResponseEntity<?>deleteOneMaintenance(@PathVariable long id_maintenance){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maintenanceService.delete(id_maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pudo eliminar, revise el pedido");
        }
    }

    @Operation(summary = "Modifica un mantenimiento", description = "Actualiza un mantenimiento existente por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mantenimiento modificado correctamente"),
            @ApiResponse(responseCode = "400", description = "Error al modificar el mantenimiento")
    })
    @PutMapping("/{id_maintenance}")
    public ResponseEntity<?> modificOneMaintenance(@PathVariable long id_maintenance, @RequestBody Maintenance maintenance){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(maintenanceService.update(id_maintenance, maintenance));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: no se pude modificar, revise de nuevo los campos");
        }
    }
}
