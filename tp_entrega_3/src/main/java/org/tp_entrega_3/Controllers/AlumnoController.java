package org.tp_entrega_3.Controllers;

import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Repositories.AlumnoRepository;

public class AlumnoController {
    private AlumnoRepository repository;

    public AlumnoController(AlumnoRepository repository) {
        this.repository = repository;
    }
    public void insert(Alumno alumno) {
        repository.save(alumno);
    }
}
