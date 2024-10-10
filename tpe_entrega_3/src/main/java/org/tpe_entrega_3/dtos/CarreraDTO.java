package org.tpe_entrega_3.dtos;
import lombok.Data;

import java.io.Serializable;

@Data
public class CarreraDTO implements Serializable {
    private long id_carrera;
    private String nombre_carrera;
    private long cant_inscriptos;
    private long egresados;
    private int anio;

    public CarreraDTO() {
    }

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
}