package org.tpe_entrega_3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tpe_entrega_3.Models.Estudia;
import org.tpe_entrega_3.Services.EstudiaService;

@RestController
@RequestMapping("/estudia")
public class EstudiaController {
    @Autowired
    private EstudiaService service;

    @GetMapping("")
    public ResponseEntity<?> getAllEstudia(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los alumnos inscriptos en carreras.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudia entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.matricularAlumnoACarrera(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }
}
