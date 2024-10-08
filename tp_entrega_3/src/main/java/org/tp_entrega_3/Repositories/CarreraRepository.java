package org.tp_entrega_3.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tp_entrega_3.Models.Carrera;
import org.tp_entrega_3.dtos.CarreraDTO;

import java.util.List;
@Repository("CarreraRepository")
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {

    @Query("SELECT new org.tp_entrega_3.dtos.CarreraDTO(c.id_carrera, c.nombre, COUNT(e.alumno.id_alumno)) FROM Carrera c JOIN c.alumnos e GROUP BY c.id_carrera, c.nombre ORDER BY COUNT(e.alumno.id_alumno) DESC")
    public List<CarreraDTO> getCarrerasConAlumnosInscriptos();

    @Query("SELECT new org.tp_entrega_3.dtos.CarreraDTO(c.nombre, e.anio_ingreso, COUNT(e)) FROM Carrera c JOIN c.alumnos e GROUP BY c.nombre, e.anio_ingreso ORDER BY c.nombre, e.anio_ingreso")
    public List<CarreraDTO> getReporteCarrerasByInscriptos();

    @Query("SELECT new org.tp_entrega_3.dtos.CarreraDTO(c.nombre, COUNT(e),e.anio_graduacion) FROM Carrera c JOIN c.alumnos e WHERE e.anio_graduacion > 0 GROUP BY c.nombre,e.anio_graduacion ORDER BY c.nombre, e.anio_graduacion")
    public List<CarreraDTO> getReporteCarrerasByEgresados();
}
