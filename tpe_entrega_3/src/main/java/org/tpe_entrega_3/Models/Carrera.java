package org.tpe_entrega_3.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Carrera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera;
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    @JsonIgnore
    private List<Estudia> alumnos;


    public Carrera(){}
    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }
}
