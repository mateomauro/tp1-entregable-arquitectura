package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrera")
public class Carrera implements Serializable{
    @Id
    @Column(name = "id_carrera")
    private Long id_Carrera;
    private String nombre;
    @OneToMany(mappedBy = "carrera")
    private List<Estudia> alumnos;

    public Carrera() {}

    public Carrera(Long id_Carrera, List<Alumno> alumnos, String nombre) {
        this.id_Carrera = id_Carrera;
        this.alumnos = new ArrayList<Estudia>();
        this.nombre = nombre;
    }
}
