package dtos;

import entities.Estudia;

import java.io.Serializable;
import java.util.List;

public class CarreraDTO implements Serializable {

    private long id_carrera;

    private String nombre_carrera;

    private long inscriptos;

    private long cant_inscriptos;

    private long egresados;

    private int anio;

    public CarreraDTO() {}

    public CarreraDTO(long id_carrera, String nombre_carrera, long can_inscriptos) {
        this.id_carrera = id_carrera;
        this.nombre_carrera = nombre_carrera;
        this.cant_inscriptos = can_inscriptos;
    }

    public CarreraDTO(String nombre, long cant_inscriptos, long cant_egresados, int anio) {
        this.nombre_carrera = nombre;
        this.inscriptos = inscriptos;
        this.egresados = cant_egresados;
        this.anio = anio;

        // | TUDAI | 10 años | inscriptos actualmente: datosAlumnos | cant egresados |
    }

    // | TUDAI | 2018 | inscriptos(loren,delfi,tomas) | cant egresados: 2 |

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

    @Override
    public String toString() {
        return "CarreraDTO{" +
                "nombre_carrera='" + nombre_carrera + '\'' +
                ", anio=" + anio +
                ", cant_inscriptos=" + cant_inscriptos +
                ", egresados=" + egresados +
                '}';
    }
}
