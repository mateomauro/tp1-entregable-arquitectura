package org.tpe_entrega_3.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlumnoDTO implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private long dni;
    private String ciudad;
    private long legajo;
    private String carrera;

    public AlumnoDTO() {}

    public AlumnoDTO(String nombre, String apellido, int edad, String genero, long dni, String ciudad, long legajo, String carrera) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.legajo = legajo;
        this.carrera = carrera;
    }
}
