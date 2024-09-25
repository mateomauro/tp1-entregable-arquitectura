package dtos;

import entities.Estudia;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;

public class AlumnoDTO implements Serializable {

    private String nombre;

    private String apellido;

    private int edad;

    private String genero;

    private int dni;

    private String ciudad;

    private int legajo;

    private String carrera;

    public AlumnoDTO() {}

    public AlumnoDTO(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int legajo, String carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.legajo = legajo;
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "AlumnoDTO{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", ciudad='" + ciudad + '\'' +
                ", carreras=" + carrera +
                ", legajo=" + legajo +
                ", genero='" + genero + '\'' +
                ", edad=" + edad +
                '}';
    }


    public String getApellido() {
        return apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getGenero() {
        return genero;
    }

    public int getDni() {
        return dni;
    }

    public String getCiudad() {
        return ciudad;
    }

    public int getLegajo() {
        return legajo;
    }

    public String getCarrera() {
        return carrera;
    }
}
