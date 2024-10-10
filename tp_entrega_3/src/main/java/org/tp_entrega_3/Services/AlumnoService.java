package org.tp_entrega_3.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.Repositories.AlumnoRepository;
import org.tp_entrega_3.dtos.AlumnoDTO;

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

    public List<Alumno> getAlumnosByOrder() throws Exception {
        try{
            return repository.getAlumnosByOrder();
        }catch (Exception e){
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

    public Alumno getAlumnoByLegajo(Integer legajo) throws Exception{
        try{
            return repository.getAlumnoByLegajo(legajo);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<Alumno> getAlumnosByGenero(String genero) throws Exception{
        try{
            return repository.getAlumnosByGenero(genero);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public List<AlumnoDTO> getAlumnosByCarreraAndCity(String carrera, String ciudad) throws Exception{
        try{
            return repository.getAlumnosByCarreraAndCity(carrera, ciudad);
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
        try {
            repository.update(id, alumno.getNombre(), alumno.getApellido(), alumno.getCiudad(), alumno.getDni(), alumno.getEdad(), alumno.getGenero(), alumno.getLegajo());
            return alumno;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Boolean deleteById(Long id) throws Exception{
        try {
            Optional<Alumno> alum = repository.getAlumnoById(id);
            if(alum != null) {
                repository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
