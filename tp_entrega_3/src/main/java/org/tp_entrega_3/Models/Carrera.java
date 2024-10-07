package org.tp_entrega_3.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Carrera {
    @Id
    private Long id_carrera;
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    private List<Estudia> alumnos;

    public Carrera(String nombre, List<Estudia> alumnos) {
        this.nombre = nombre;
        this.alumnos = alumnos;
    }
    public Carrera(){}

    @Override
    public String toString() {
        return "Carrera{" +
                "id_carrera=" + id_carrera +
                ", nombre='" + nombre + '\'' +
                ", alumnos=" + alumnos +
                '}';
    }
}
