package org.tp_entrega_3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tp_entrega_3.Repositories.CarreraRepository;
import org.tp_entrega_3.Services.CarreraService;
import org.tp_entrega_3.dtos.CarreraDTO;

import java.util.*;
@RestController
@RequestMapping("/carreras")
public class CarreraController {
    @Autowired
    private CarreraService service;

    @GetMapping("")
    public ResponseEntity<?> getAllCarreras(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todas las carreras.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCarreraById(@PathVariable int id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo la carrera por ID.\"}");
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> getCarreraByName(@PathVariable String name){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findByName(name));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo la carrera por ID.\"}");
        }
    }

    // f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
    @GetMapping("/CarrerasConAlumnosInscriptos")
    public ResponseEntity<?> getCarrerasConAlumnosInscriptos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getCarrerasConAlumnosInscriptos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para obtener las carreras con alumnos inscriptos\"}");
        }
    }

    // h) generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    // presentar los años de manera cronológica.
    @GetMapping("/ReporteCarreras")
    public ResponseEntity<?> getReporteCarreras() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReporteCarreras());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde para obtener el Reporte de Carreras\"}");
        }
    }
}
