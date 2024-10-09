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

    @GetMapping("/CarrerasConAlumnosInscriptos")
    public ResponseEntity<?> getCarrerasConAlumnosInscriptos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getCarrerasConAlumnosInscriptos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde\"}");
        }
    }

    @GetMapping("/ReporteCarreras")
    public ResponseEntity<?> getReporteCarreras() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getReporteCarreras());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde\"}");
        }
    }
}
