package org.tp_entrega_3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tp_entrega_3.Models.Alumno;

import java.util.List;
@Repository("AlumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

    @Query("SELECT a FROM Alumno a where a.nombre = :nombre")
    public List<Alumno> findByNombre(String nombre);

    @Query("SELECT a FROM Alumno a ORDER BY a.edad")
    public List<Alumno> getAlumnosByOrder();

    @Query("SELECT a FROM Alumno a WHERE a.legajo = :legajo")
    public Alumno alumnoByLegajo(Integer legajo);

    @Query("SELECT a FROM Alumno a WHERE a.genero = :genero")
    public List<Alumno> getAlumnosByGenero(String genero);

    @Query("SELECT new org.tp_entrega_3.dtos.AlumnoDTO(a.nombre, a.apellido, a.edad, a.genero, a.dni, a.ciudad, a.legajo, e.carrera.nombre) FROM Alumno a JOIN a.carreras e WHERE e.carrera.nombre = :carrera AND a.ciudad = :ciudad")
    public List<Alumno> getAlumnosByCarreraAndCity(String carrera, String ciudad);
}
