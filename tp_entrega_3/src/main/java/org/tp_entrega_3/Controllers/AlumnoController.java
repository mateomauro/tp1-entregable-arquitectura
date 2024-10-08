package org.tp_entrega_3.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Repositories.AlumnoRepository;
import org.tp_entrega_3.Services.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAllAlumnos() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde obteniendo todos los alumnos.\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAlumnoById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.getAlumnoById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde buscando por ID.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Alumno entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo ingresar, revise los campos e intente nuevamente.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Alumno entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(alumnoService.update(id,entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. No se pudo editar, revise los campos e intente nuevamente.\"}");
        }
    }
    /**
     *Repo
     *  @Modifying
     *     @Transactional
     *     @Query(value = "UPDATE Alumno SET nombre = :nombre, apellido = :apellido, edad = :edad WHERE id = :id", nativeQuery = true)
     *     int updateAlumno(@Param("nombre") String nombre, @Param("apellido") String apellido, @Param("edad") int edad, @Param("id") Long id);
     * Service
     *     public void actualizarAlumno(Long id, String nombre, String apellido, int edad) {
     *         alumnoRepositorio.updateAlumno(nombre, apellido, edad, id);
     *     }
     * Controller
     *     @PutMapping("/{id}")
     *     public ResponseEntity<String> actualizarAlumno(@RequestBody Alumno alumno) {
     *         alumnoServicio.actualizarAlumno(alumno.getId(), alumno.getNombre(), alumno.getApellido(), alumno.getEdad());
     *         return ResponseEntity.ok("Alumno actualizado exitosamente"
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * {
     *         "id_alumno": 0,
     *         "nombre": "loren",
     *         "apellido": "feer",
     *         "ciudad": "tandil",
     *         "dni": 123123,
     *         "edad": 25,
     *         "genero": "masculino",
     *         "legajo": 1223123,
     *         "carreras": []
     *     },
     *     {
     *         "id_alumno": 1,
     *         "nombre": "el perro el tomi",
     *         "apellido": "donati",
     *         "ciudad": "tandil",
     *         "dni": 56498741,
     *         "edad": 22,
     *         "genero": "anda a saber",
     *         "legajo": 322165,
     *         "carreras": []
     *     },
     *     {
     *         "id_alumno": 2,
     *         "nombre": "la perra delfi",
     *         "apellido": "ferreyra",
     *         "ciudad": "la plata",
     *         "dni": 567986451,
     *         "edad": 223,
     *         "genero": "bue mejor ni hablar",
     *         "legajo": 59894,
     *         "carreras": []
     *     }
     */
}
