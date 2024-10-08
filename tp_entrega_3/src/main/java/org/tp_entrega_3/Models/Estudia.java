package org.tp_entrega_3.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class Estudia implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "id_carrera", referencedColumnName = "id_carrera")
    private Carrera carrera;
    @Id
    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id_alumno")
    private Alumno alumno;
    private int anio_ingreso;
    private int anio_graduacion;

    public Estudia(){}
    public Estudia(Carrera carrera, Alumno alumno, int anio_ingreso, int anio_graduacion) {
        this.carrera = carrera;
        this.alumno = alumno;
        this.anio_ingreso = anio_ingreso;
        this.anio_graduacion = anio_graduacion;
    }

    @Override
    public String toString() {
        return "Estudia{" +
                "id_carrera=" + carrera +
                ", id_alumno=" + alumno +
                ", anio_ingreso=" + anio_ingreso +
                ", anio_graduacion=" + anio_graduacion +
                '}';
    }
}
