package org.tp_entrega_3.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Estudia implements Serializable {

    @EmbeddedId
    EstudiaID ID = new EstudiaID();
    @ManyToOne
    @MapsId("id_carrera")
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;
    @ManyToOne
    @MapsId("id_alumno")
    @JoinColumn(name = "id_alumno")
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
}
