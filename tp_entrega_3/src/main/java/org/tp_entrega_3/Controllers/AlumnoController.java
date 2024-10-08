package org.tp_entrega_3.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Repositories.AlumnoRepository;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    private final AlumnoRepository repository;

    public AlumnoController(AlumnoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAlumnos(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
