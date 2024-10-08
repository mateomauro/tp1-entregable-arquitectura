package org.tp_entrega_3.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Repositories.AlumnoRepository;

import java.util.List;
import java.util.Optional;

@Service("AlumnoService")
public class AlumnoService {
    @Autowired
    private AlumnoRepository repository;

    public List<Alumno> findAll() throws Exception {
        try {
            return repository.findAll();
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Optional<Alumno> getAlumnoById(Long id) throws Exception{
        try{
            return repository.getAlumnoById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //@Transactional
    public Alumno save(Alumno entity) throws Exception {
        try{
            return repository.save(entity);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Alumno update(Long id, Alumno alumno) throws Exception {
        try{
            return repository.update(id, alumno.getNombre(), alumno.getApellido(), alumno.getCiudad(), alumno.getDni(), alumno.getEdad(), alumno.getGenero(), alumno.getLegajo());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
