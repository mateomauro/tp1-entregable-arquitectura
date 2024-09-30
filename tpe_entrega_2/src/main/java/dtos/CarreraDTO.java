package dtos;

import entities.Estudia;

import java.io.Serializable;
import java.util.List;

public class CarreraDTO implements Serializable {

    private long id_carrera;

    private String nombre_carrera;

    private List<Estudia> inscriptos;

    private long cant_inscriptos;

    private long cant_egresados;

    private int anio_graduacion;

    public CarreraDTO() {}

    public CarreraDTO(long id_carrera, String nombre_carrera, long can_inscriptos) {
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.cant_inscriptos = can_inscriptos;
    }

    public CarreraDTO(String nombre, long cant_egresados, int anio) {
        this.nombre_carrera = nombre;
        //this.inscriptos = inscriptos;
        this.cant_egresados = cant_egresados;
        this.anio_graduacion = anio;

        // | TUDAI | 10 años | inscriptos actualmente: datosAlumnos | cant egresados |
    }

    public String toString(){
        return "| Carrera: " + this.nombre_carrera + " | inscriptos: " + this.cant_inscriptos + " |";
    }

    public long getId_carrera() {
        return id_carrera;
    }

    public void setId_carrera(long id_carrera) {
        this.id_carrera = id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    public long getCant_inscriptos() {
        return cant_inscriptos;
    }

    public void setCant_inscriptos(int cant_inscriptos) {
        this.cant_inscriptos = cant_inscriptos;
    }
}
