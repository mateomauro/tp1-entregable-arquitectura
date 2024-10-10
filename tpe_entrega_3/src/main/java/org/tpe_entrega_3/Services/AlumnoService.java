package org.tpe_entrega_3.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tpe_entrega_3.Models.Alumno;
import org.tpe_entrega_3.Repositories.AlumnoRepository;
import org.tpe_entrega_3.dtos.AlumnoDTO;

import java.util.List;

@Service("AlumnoService")
public class AlumnoService {
    @Autowired
    private AlumnoRepository repository;

    public List<Alumno> findAll() throws Exception {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    // a) dar de alta un estudiante
    public Alumno save(Alumno entity) throws Exception {
        try{
            return repository.save(entity);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    public List<Alumno> getAlumnosByOrder() throws Exception {
        try{
            return repository.getAlumnosByOrder();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Alumno getAlumnoById(int id) throws Exception{
        try{
            return repository.getAlumnoById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Alumno getAlumnoByDNI(int dni) throws Exception{
        try{
            return repository.getAlumnoByDNI(dni);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // d) recuperar un estudiante, en base a su número de libreta universitaria.
    public Alumno getAlumnoByLegajo(Integer legajo) throws Exception{
        try{
            return repository.getAlumnoByLegajo(legajo);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // e) recuperar todos los estudiantes, en base a su género.
    public List<Alumno> getAlumnosByGenero(String genero) throws Exception{
        try{
            return repository.getAlumnosByGenero(genero);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    public List<AlumnoDTO> getAlumnosByCarreraAndCiudad(String carrera, String ciudad) throws Exception{
        try{
            return repository.getAlumnosByCarreraAndCiudad(carrera, ciudad);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Alumno update(int id, Alumno alumno) throws Exception {
        try {
            repository.update(id, alumno.getNombre(), alumno.getApellido(), alumno.getCiudad(), alumno.getDni(), alumno.getEdad(), alumno.getGenero(), alumno.getLegajo());
            return alumno;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
