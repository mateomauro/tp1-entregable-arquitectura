package dtos;

import java.io.Serializable;

public class CarreraDTO implements Serializable {

    private long id_carrera;

    private String nombre_carrera;

    private long inscriptos;

    private long cant_inscriptos;

    private long cant_egresados;

    private long anio;

    public CarreraDTO() {}

    public CarreraDTO(long id_carrera, String nombre_carrera, long can_inscriptos) {
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.cant_inscriptos = can_inscriptos;
    }

    public CarreraDTO(String nombre, long cant_inscriptos, long cant_egresados, long anio) {
        this.nombre_carrera = nombre;
        this.inscriptos = cant_inscriptos;
        this.cant_egresados = cant_egresados;
        this.anio = anio;

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
