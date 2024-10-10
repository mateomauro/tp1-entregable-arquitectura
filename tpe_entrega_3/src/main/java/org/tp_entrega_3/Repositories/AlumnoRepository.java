package org.tp_entrega_3.Repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tp_entrega_3.Models.Alumno;
import org.tp_entrega_3.dtos.AlumnoDTO;

import java.util.List;

@Repository("AlumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("SELECT a FROM Alumno a WHERE a.id_alumno = :id")
    public Alumno getAlumnoById(int id);

    @Query("SELECT a FROM Alumno a WHERE a.dni = :dni")
    public Alumno getAlumnoByDNI(int dni);

    // c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
    @Query("SELECT a FROM Alumno a ORDER BY a.edad")
    public List<Alumno> getAlumnosByOrder();

    // d) recuperar un estudiante, en base a su número de libreta universitaria.
    @Query("SELECT a FROM Alumno a WHERE a.legajo = :legajo")
    public Alumno getAlumnoByLegajo(Integer legajo);

    // e) recuperar todos los estudiantes, en base a su género.
    @Query("SELECT a FROM Alumno a WHERE a.genero = :genero")
    public List<Alumno> getAlumnosByGenero(String genero);

    // g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @Query("SELECT new org.tp_entrega_3.dtos.AlumnoDTO(a.nombre, a.apellido, a.edad, a.genero, a.dni, a.ciudad, a.legajo, e.carrera.nombre) FROM Alumno a JOIN a.carreras e WHERE e.carrera.nombre = :carrera AND a.ciudad = :ciudad")
    public List<AlumnoDTO> getAlumnosByCarreraAndCiudad(String carrera, String ciudad);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Alumno SET nombre = :nombre, apellido = :apellido, ciudad = :ciudad, dni = :dni, edad = :edad, genero = :genero, legajo = :legajo WHERE id_alumno = :id", nativeQuery = true)
    void update(@Param("id") int id, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("ciudad") String ciudad, @Param("dni") int dni, @Param("edad") int edad, @Param("genero") String genero, @Param("legajo") int legajo);

}
