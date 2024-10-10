package org.tpe_entrega_3.Models;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class EstudiaID implements Serializable {
    private int id_carrera;
    private int id_alumno;
    public EstudiaID() {
    }
}
