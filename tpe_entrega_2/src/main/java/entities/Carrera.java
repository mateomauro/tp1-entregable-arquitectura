package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Carrera implements Serializable{
    @Id
    private int id_Carrera;
    @Column
    private String nombre;
    @OneToMany
    private List<Alumno> alumnos;

    public Carrera() {}

}
