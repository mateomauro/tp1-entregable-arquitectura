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

    //este constructor es para combinar ambos constructores
    public CarreraDTO(String nombre, long cant_inscriptos, long cant_egresados, int anio) {
        this.nombre_carrera = nombre;
        this.cant_inscriptos = cant_inscriptos;
        this.egresados = cant_egresados;
        this.anio = anio;
    }


    //creamos este constructur para devolver la consulta jpql de el ejercicio 3
    //y conseguir la cantidad de inscriptos
    public CarreraDTO(String nombre, int anio, long cant_inscriptos) {
        this.nombre_carrera = nombre;
        this.cant_inscriptos = cant_inscriptos;
        this.anio = anio;
    }

    //creamos este constructur para devolver la consulta jpql de el ejercicio 3
    //y conseguir la cantidad de egresados
    public CarreraDTO(String nombre, long cant_egresados, int anio) {
        this.nombre_carrera = nombre;
        this.egresados = cant_egresados;
        this.anio = anio;
    }


    public long getId_carrera() {
        return id_carrera;
    }

    public String getNombre_carrera() {
        return nombre_carrera;
    }

    public long getInscriptos() {
        return inscriptos;
    }

    public long getCant_inscriptos() {
        return cant_inscriptos;
    }

    public long getEgresados() {
        return egresados;
    }

    public int getAnio() {
        return anio;
    }

    public void setId_carrera(long id_carrera) {
        this.id_carrera = id_carrera;
    }

    public void setEgresados(long egresados) {
        this.egresados = egresados;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setCant_inscriptos(long cant_inscriptos) {
        this.cant_inscriptos = cant_inscriptos;
    }

    public void setInscriptos(long inscriptos) {
        this.inscriptos = inscriptos;
    }

    public void setNombre_carrera(String nombre_carrera) {
        this.nombre_carrera = nombre_carrera;
    }

    @Override
    public String toString() {
        if (anio == 0){
            return "CarreraDTO{" +
                    "nombre_carrera='" + nombre_carrera + '\'' +
                    ", cant_inscriptos=" + cant_inscriptos +
                    ", egresados=" + egresados +
                    '}';
        }else {
            return "CarreraDTO{" +
                "nombre_carrera='" + nombre_carrera + '\'' +
                ", anio=" + anio +
                ", cant_inscriptos=" + cant_inscriptos +
                ", egresados=" + egresados +
                '}';
        }
    }
}
