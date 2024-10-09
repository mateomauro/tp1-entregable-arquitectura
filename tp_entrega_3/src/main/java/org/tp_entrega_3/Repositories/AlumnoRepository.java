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
import java.util.Optional;

@Repository("AlumnoRepository")
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("SELECT a FROM Alumno a where a.nombre = :nombre")
    public List<Alumno> findByNombre(String nombre);

    @Query("SELECT a FROM Alumno a WHERE a.id_alumno = :id")
    public Optional<Alumno> getAlumnoById(Long id);

    @Query("SELECT a FROM Alumno a ORDER BY a.edad")
    public List<Alumno> getAlumnosByOrder();

    @Query("SELECT a FROM Alumno a WHERE a.legajo = :legajo")
    public Alumno getAlumnoByLegajo(Integer legajo);

    @Query("SELECT a FROM Alumno a WHERE a.genero = :genero")
    public List<Alumno> getAlumnosByGenero(String genero);

    @Query("SELECT new org.tp_entrega_3.dtos.AlumnoDTO(a.nombre, a.apellido, a.edad, a.genero, a.dni, a.ciudad, a.legajo, e.carrera.nombre) FROM Alumno a JOIN a.carreras e WHERE e.carrera.nombre = :carrera AND a.ciudad = :ciudad")
    public List<AlumnoDTO> getAlumnosByCarreraAndCity(String carrera, String ciudad);

    //@Modifying
    //@Query(value = "UPDATE Alumno a SET a.nombre = :alumno.nombre, a.apellido = :alumno.apellido, a.ciudad = :alumno.ciudad, a.dni = :alumno.dni, a.edad = :alumno.edad, a.genero = :alumno.genero, a.legajo = :alumno.legajo WHERE a.id_alumno = :id", nativeQuery = true)
    //public Alumno update(Long id, Alumno alumno);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Alumno SET nombre = :nombre, apellido = :apellido, ciudad = :ciudad, dni = :dni, edad = :edad, genero = :genero, legajo = :legajo WHERE id_alumno = :id", nativeQuery = true)
    void update(@Param("id") Long id, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("ciudad") String ciudad, @Param("dni") Long dni, @Param("edad") int edad, @Param("genero") String genero, @Param("legajo") Long legajo);

}
