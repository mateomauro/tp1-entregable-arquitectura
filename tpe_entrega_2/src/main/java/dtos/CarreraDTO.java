package dtos;

import entities.Estudia;

import java.io.Serializable;
import java.util.List;

public class CarreraDTO implements Serializable {

    private long id_carrera;

    private String nombre_carrera;

    private List<Estudia> alumnos;

    private long can_inscriptos;

    public CarreraDTO() {}

    public CarreraDTO(long id_carrera, String nombre_carrera, long can_inscriptos) {
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.can_inscriptos = can_inscriptos;
    }

    public String toString(){
        return "| Carrera: " + this.nombre_carrera + " | inscriptos: " + this.can_inscriptos + " |";
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

    public List<Estudia> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Estudia> alumnos) {
        this.alumnos = alumnos;
    }

    public long getCan_inscriptos() {
        return can_inscriptos;
    }

    public void setCan_inscriptos(int can_inscriptos) {
        this.can_inscriptos = can_inscriptos;
    }
}
