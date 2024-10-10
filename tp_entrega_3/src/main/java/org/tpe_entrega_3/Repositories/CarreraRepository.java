package org.tp_entrega_3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tp_entrega_3.Models.Carrera;
import org.tp_entrega_3.dtos.CarreraDTO;

import java.util.List;
@Repository("CarreraRepository")
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT c FROM Carrera c WHERE c.id_carrera = :id")
    public Carrera getCarreraById(int id);

    @Query("SELECT c FROM Carrera c WHERE c.nombre = :name")
    public Carrera getCarreraByName(String name);

    // f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos
    @Query("SELECT new org.tp_entrega_3.dtos.CarreraDTO(c.id_carrera, c.nombre, COUNT(e.alumno.id_alumno)) FROM Carrera c JOIN c.alumnos e GROUP BY c.id_carrera, c.nombre ORDER BY COUNT(e.alumno.id_alumno) DESC")
    public List<CarreraDTO> getCarrerasConAlumnosInscriptos();

    // h) generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y
    // presentar los años de manera cronológica.

    // El enunciado h) se divide en 2 querys distintas que la primera trae los inscriptos por carrera y la
    // segunda los egresados de las carreras.
    // Primer consulta:
    @Query("SELECT new org.tp_entrega_3.dtos.CarreraDTO(c.nombre, e.anio_ingreso, COUNT(e)) FROM Carrera c JOIN c.alumnos e GROUP BY c.nombre, e.anio_ingreso ORDER BY c.nombre, e.anio_ingreso")
    public List<CarreraDTO> getReporteCarrerasByInscriptos();
    // Segunda consulta:
    @Query("SELECT new org.tp_entrega_3.dtos.CarreraDTO(c.nombre, COUNT(e),e.anio_graduacion) FROM Carrera c JOIN c.alumnos e WHERE e.anio_graduacion > 0 GROUP BY c.nombre,e.anio_graduacion ORDER BY c.nombre, e.anio_graduacion")
    public List<CarreraDTO> getReporteCarrerasByEgresados();
}
