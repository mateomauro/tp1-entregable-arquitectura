package org.tp_entrega_3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Services.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("")
    public ResponseEntity<?> getAllAlumnos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los alumnos.\"}");
        }
    }

    // a) dar de alta un estudiante
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Alumno entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    // c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    @GetMapping("/OrderByEdad")
    public ResponseEntity<?> getAlumnosByOrder() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnosByOrder());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los alumnos ordenados por Edad.\"}");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAlumnoById(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde buscando por ID.\"}");
        }
    }

    @GetMapping("/ByDNI/{dni}")
    public ResponseEntity<?> getAlumnoByDNI(@PathVariable int dni) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoByDNI(dni));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde buscando por ID.\"}");
        }
    }

    // d) recuperar un estudiante, en base a su número de libreta universitaria.
    @GetMapping("/ByLegajo/{legajo}")
    public ResponseEntity<?> getAlumnoByLegajo(@PathVariable Integer legajo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoByLegajo(legajo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde buscando por ID.\"}");
        }
    }

    // e) recuperar todos los estudiantes, en base a su género.
    @GetMapping("/ByGenero/{genero}")
    public ResponseEntity<?> getAlumnosByGenero(@PathVariable String genero) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnosByGenero(genero));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los alumnos.\"}");
        }
    }

    // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @GetMapping("/ByCarreraAndCiudad/{carrera}/{ciudad}")
    public ResponseEntity<?> getAlumnosByCarreraAndCiudad(@PathVariable String carrera, @PathVariable String ciudad) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnosByCarreraAndCiudad(carrera,ciudad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde buscando por Carrera y Ciudad.\"}");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable int id, @RequestBody Alumno entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }
}
