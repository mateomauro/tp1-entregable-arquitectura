package org.tp_entrega_3.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Alumno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_alumno;
    private String nombre;
    private String apellido;
    private String ciudad;
    private int dni;
    private int edad;
    private String genero;
    private int legajo;
    @OneToMany(mappedBy = "alumno")
    @JsonIgnore
    private List<Estudia> carreras;

    public Alumno(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int legajo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.legajo = legajo;
        this.carreras = new ArrayList<Estudia>();
    }

    public Alumno() {
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", dni=" + dni +
                ", edad=" + edad +
                ", genero='" + genero + '\'' +
                ", legajo=" + legajo +
                '}';
    }
}
